package com.nosy.admin.nosyadmin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nosy.admin.nosyadmin.config.KeycloakConfigBean;
import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.model.User;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Arrays.asList;

@Component
public class KeycloakClient {

  private static final Logger logger = LoggerFactory.getLogger(KeycloakClient.class);
  private static final String GRANT_TYPE_STRING = "grant_type";
  private static final String CLIENT_ID_STRING = "client_id";
  private static final String CLIENT_SECRET_STRING = "client_secret";

  @Value("${nosy.client.keycloak.url}")
  private String keycloakUrl;

  @Value("${nosy.client.clientSecret}")
  private String clientSecret;

  @Value("${keycloak.resource}")
  private String clientId;

  @Value("${keycloak.auth-server-url}")
  private String keycloakAdminUrl;

  @Value("${nosy.keycloak.admin.user}")
  private String keycloakAdminUser;

  @Value("${nosy.keycloak.admin.password}")
  private String keycloakAdminPassword;

  @Value("${keycloak.realm}")
  private String keycloakRealm;

  @Value("${nosy.client.grantType}")
  private String grantType;

  @Autowired private ClientToken clientToken;
  @Autowired private TokenCollection tokenCollection;
  @Autowired private KeycloakConfigBean keycloakConfigBean;

  @Value("${nosy.client.refreshToken}")
  private String refreshToken;

  public boolean isAuthenticated(String token) throws IOException {

    HttpPost post = new HttpPost(keycloakUrl + "/introspect");
    String tokenString = "token";
    List<NameValuePair> params =
        asList(
            new BasicNameValuePair(GRANT_TYPE_STRING, grantType),
            new BasicNameValuePair(CLIENT_ID_STRING, clientId),
            new BasicNameValuePair(tokenString, token),
            new BasicNameValuePair(CLIENT_SECRET_STRING, clientSecret));

    post.setEntity(new UrlEncodedFormEntity(params));
    post.addHeader("Content-Type", "application/x-www-form-urlencoded");
    return keycloakConfigBean.requestInterceptor(post);
  }

  public void logoutUser(String username) {

    UsersResource usersResource = keycloakConfigBean.getKeycloakUserResource().users();

    if(usersResource.get(getUserGet(username).get())!=null){
      usersResource.get(getUserGet(username).get()).logout();
    }

  }

  public void deleteUsername(String username) {
    UsersResource usersResource = keycloakConfigBean.getKeycloakUserResource().users();

    usersResource.delete(getUserGet(username).get());
  }

  public User getUserInfo(String username) {
    UsersResource userResource = keycloakConfigBean.getKeycloakUserResource().users();
    User user = new User();
    userResource
        .list()
        .forEach(
            t -> {
              if (username.equals(t.getUsername())) {
                user.setEmail(t.getEmail());
                user.setFirstName(t.getFirstName());
                user.setLastName(t.getLastName());
              }
            });
    return user;
  }

  private AtomicReference<String> getUserGet(String username) {
    UsersResource usersResource = keycloakConfigBean.getKeycloakUserResource().users();
    AtomicReference<String> userId = new AtomicReference<>("");
    usersResource
        .list()
        .forEach(
            t -> {
              if (username.equals(t.getUsername())) {
                userId.set(t.getId());
              }
            });
    return userId;
  }

  public boolean registerNewUser(User user) {

    int statusId;
    try {
      RealmResource realmResource = keycloakConfigBean.getKeycloakUserResource();
      UsersResource usersResource = realmResource.users();

      UserRepresentation newUser = new UserRepresentation();
      newUser.setUsername(user.getEmail());
      newUser.setEmail(user.getEmail());
      newUser.setFirstName(user.getFirstName());
      newUser.setLastName(user.getLastName());
      newUser.setEnabled(true);
      Response result = usersResource.create(newUser);

      statusId = result.getStatus();

      if (statusId == 201) {

        String userId = result.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(user.getPassword());

        usersResource.get(userId).resetPassword(passwordCred);

        ClientRepresentation clientRep = realmResource.clients().findByClientId(clientId).get(0);
        RoleRepresentation clientRoleRep =
            realmResource
                .clients()
                .get(clientRep.getId())
                .roles()
                .get("nosy-role")
                .toRepresentation();
        realmResource
            .users()
            .get(userId)
            .roles()
            .clientLevel(clientRep.getId())
            .add(Arrays.asList(clientRoleRep));

      } else {
        return false;
      }

    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }

    return true;
  }





  public ClientToken getTokens(User user) throws IOException {

    HttpPost post = new HttpPost(keycloakUrl);
    List<NameValuePair> params =
        asList(
            new BasicNameValuePair(GRANT_TYPE_STRING, grantType),
            new BasicNameValuePair(CLIENT_ID_STRING, clientId),
            new BasicNameValuePair("username", user.getEmail()),
            new BasicNameValuePair("password", user.getPassword()),
            new BasicNameValuePair(CLIENT_SECRET_STRING, clientSecret));

    post.setEntity(new UrlEncodedFormEntity(params));
    post.addHeader("Content-Type", "application/x-www-form-urlencoded");
    ClientToken clientTokenCollection = getTokenCollection(post);
    if (clientTokenCollection == null || clientTokenCollection.getAccessToken() == null) {
      throw new GeneralException("Invalid Username or Password");
    }
    return getTokenCollection(post);
  }

  private ClientToken getTokenCollection(HttpPost post) throws IOException {
    try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
      return httpclient.execute(
          post,
          response -> {
            ObjectMapper mapper = new ObjectMapper();
            int status = response.getStatusLine().getStatusCode();

            if (status >= 200 && status < 300) {
              tokenCollection =
                  mapper.readValue(response.getEntity().getContent(), TokenCollection.class);
              clientToken.setAccessToken(tokenCollection.getAccessToken());
              clientToken.setRefreshToken(tokenCollection.getRefreshToken());
              clientToken.setExpiresIn(tokenCollection.getExpiresIn());
              return clientToken;

            } else {
              return null;
            }
          });
    }
  }


}
