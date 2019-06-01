package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.config.KeycloakConfigBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeycloakServiceTest {
    @InjectMocks
    KeycloakService keycloakService;

    @Mock
    KeycloakConfigBean keycloakConfigBean;

    @Test
    public void isAuthenticatedFalse() {
        String token="addsada";

        when(keycloakConfigBean.getPostForAuthentication(any())).thenReturn(false);

        assertFalse(keycloakService.isAuthenticated(token));
    }


    @Test
    public void isAuthenticatedTrue() {
        String token="addsada";
        when(keycloakConfigBean.getPostForAuthentication(any())).thenReturn(true);
        assertTrue(keycloakService.isAuthenticated(token));

    }
    @Test(expected = Test.None.class)
    public void logoutUserNotFound() {
        String username="dadas";
        RealmResource realmResource=mock(RealmResource.class);
        UsersResource usersResource=mock(UsersResource.class);
        when(keycloakConfigBean.getKeycloakUserResource()).thenReturn(realmResource);
        when(realmResource.users()).thenReturn(usersResource);
        keycloakService.logoutUser(username);

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
        keycloakService.logoutUser(username);

    }
    @Test(expected = Test.None.class)
    public void deleteUsername() {
        String username="dadas";
        RealmResource realmResource=mock(RealmResource.class);
        UsersResource usersResource=mock(UsersResource.class);
        when(keycloakConfigBean.getKeycloakUserResource()).thenReturn(realmResource);
        when(realmResource.users()).thenReturn(usersResource);
        keycloakService.deleteUsername(username);

    }

    @Test
    public void getUserInfo() {
        String username="dadas";
        UsersResource usersResource=mock(UsersResource.class);
        RealmResource realmResource=mock(RealmResource.class);
        when(keycloakConfigBean.getKeycloakUserResource()).thenReturn(realmResource);
        when(realmResource.users()).thenReturn(usersResource);
        assertNotNull(keycloakService.getUserInfo(username));
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
