package com.nosy.admin.nosyadmin.config.security;

import com.nosy.admin.nosyadmin.config.KeycloakConfigBean;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import sun.security.krb5.Realm;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeycloakClientTest {
    @InjectMocks
    KeycloakClient keycloakClient;

    @Mock
    KeycloakConfigBean keycloakConfigBean;

    @Test
    public void isAuthenticated() throws IOException {
        String token="addsada";
        when(keycloakConfigBean.requestInterceptor(any())).thenReturn(true);
        assertTrue(keycloakClient.isAuthenticated(token));
        when(keycloakConfigBean.requestInterceptor(any())).thenReturn(false);

        assertFalse(keycloakClient.isAuthenticated(token));
    }

    @Test(expected = Test.None.class)
    public void logoutUserNotFound() {
        String username="dadas";
        RealmResource realmResource=mock(RealmResource.class);
        UsersResource usersResource=mock(UsersResource.class);
        when(keycloakConfigBean.getKeycloakUserResource()).thenReturn(realmResource);
        when(realmResource.users()).thenReturn(usersResource);
        keycloakClient.logoutUser(username);

    }
    @Test(expected = Test.None.class)
    public void logoutUserSuccess() {
        String username="dadas";
        RealmResource realmResource=mock(RealmResource.class);
        UsersResource usersResource=mock(UsersResource.class);
        when(keycloakConfigBean.getKeycloakUserResource()).thenReturn(realmResource);
        when(realmResource.users()).thenReturn(usersResource);
        UserResource userResource=mock(UserResource.class);
        when(usersResource.get(any())).thenReturn(userResource);
        keycloakClient.logoutUser(username);

    }
//
//    public void logoutUser(String username) {
//
//        UsersResource userRessource = keycloakConfigBean.getKeycloakUserResource().users();
//
//        userRessource.get(getUserGet(username).get()).logout();
//    }
//
//    public void deleteUsername(String username) {
//        UsersResource userRessource = keycloakConfigBean.getKeycloakUserResource().users();
//
//        userRessource.delete(getUserGet(username).get());
//    }


    @Test
    public void deleteUsername() {
    }

    @Test
    public void getUserInfo() {
    }

    @Test
    public void registerNewUser() {
    }

    @Test
    public void requestInterceptor() {
    }

    @Test
    public void getTokens() {
    }
}
