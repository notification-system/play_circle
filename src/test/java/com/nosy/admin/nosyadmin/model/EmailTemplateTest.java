package com.nosy.admin.nosyadmin.model;

import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class EmailTemplateTest {

    @Test(expected = GeneralException.class)
    public void onCreate() {
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.onCreate();
    }
    @Test(expected = GeneralException.class)
    public void onCreateEmpty() {
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.setEmailTemplateName("");
        emailTemplate.onCreate();
    }
    @Test(expected = Test.None.class)
    public void onCreateSuccess() {
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.setEmailTemplateName("dasdas");
        emailTemplate.onCreate();
    }




}
