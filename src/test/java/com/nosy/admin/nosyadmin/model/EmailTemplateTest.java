package com.nosy.admin.nosyadmin.model;

import com.nosy.admin.nosyadmin.exceptions.EmailTemplateNameInvalidException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class EmailTemplateTest {

    @Test(expected = EmailTemplateNameInvalidException.class)
    public void onCreate() {
        EmailTemplate emailTemplate=new EmailTemplate();
        emailTemplate.onCreate();
    }
    @Test(expected = EmailTemplateNameInvalidException.class)
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
