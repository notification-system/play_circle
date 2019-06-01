package com.nosy.admin.nosyadmin.config.security;

import com.nosy.admin.nosyadmin.config.KeycloakConfigBean;
import org.apache.http.client.methods.HttpPost;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KeycloakClientTest {
    @InjectMocks
    KeycloakClient keycloakClient;

    @Mock
    KeycloakConfigBean keycloakConfigBean;
//    public boolean isAuthenticated(String token) throws IOException {
//
//        HttpPost post = new HttpPost(keycloakUrl + "/introspect");
//        String tokenString = "token";
//        List<NameValuePair> params =
//                asList(
//                        new BasicNameValuePair(GRANT_TYPE_STRING, grantType),
//                        new BasicNameValuePair(CLIENT_ID_STRING, clientId),
//                        new BasicNameValuePair(tokenString, token),
//                        new BasicNameValuePair(CLIENT_SECRET_STRING, clientSecret));
//
//        post.setEntity(new UrlEncodedFormEntity(params));
//        post.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        return requestInterceptor(post);
//    }
    @Test
    public void isAuthenticated() throws IOException {
        String token="addsada";
        HttpPost post = mock(HttpPost.class);
        when(keycloakConfigBean.requestInterceptor(any())).thenReturn(true);
        assertTrue(keycloakClient.isAuthenticated(token));
        when(keycloakConfigBean.requestInterceptor(any())).thenReturn(false);

        assertFalse(keycloakClient.isAuthenticated(token));
    }

    @Test
    public void logoutUser() {
    }

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
