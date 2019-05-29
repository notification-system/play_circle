package com.nosy.admin.nosyadmin.model;

import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class EmailTemplateTest {

    @Test(expected = GeneralException.class)
    public void onCreate() {
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.onCreate();
    }
}
