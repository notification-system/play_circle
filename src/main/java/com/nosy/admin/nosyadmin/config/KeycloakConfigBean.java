package com.nosy.admin.nosyadmin.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakConfigBean {
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

    public RealmResource getKeycloakUserResource() {

        Keycloak kc =
                KeycloakBuilder.builder()
                        .serverUrl(keycloakAdminUrl)
                        .realm(keycloakRealm)
                        .username(keycloakAdminUser)
                        .password(keycloakAdminPassword)
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                        .build();

        return kc.realm(keycloakRealm);
    }
}
