package com.nosy.admin.nosyadmin.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.keycloak.admin.client.resource.RealmResource;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.web.header.writers.frameoptions.RegExpAllowFromStrategy;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class KeycloakConfigBeanTest {


    @InjectMocks
    private KeycloakConfigBean keycloakConfigBean;

    @Before
    public void setUp(){
        ReflectionTestUtils.setField(keycloakConfigBean, "keycloakUrl", "asdasd");
        ReflectionTestUtils.setField(keycloakConfigBean, "clientSecret", "test-client");
        ReflectionTestUtils.setField(keycloakConfigBean, "clientId", "dasda");
        ReflectionTestUtils.setField(keycloakConfigBean, "keycloakAdminUrl", "dasdadsdass");
        ReflectionTestUtils.setField(keycloakConfigBean, "keycloakAdminUser", "dasdadsdass");
        ReflectionTestUtils.setField(keycloakConfigBean, "keycloakAdminPassword", "dasdadsdass");
        ReflectionTestUtils.setField(keycloakConfigBean, "keycloakRealm", "dasdadsdass");

    }

    @Test(expected = Test.None.class)
    public void getKeycloakUserResource() {
        keycloakConfigBean.getKeycloakUserResource();
    }
}
