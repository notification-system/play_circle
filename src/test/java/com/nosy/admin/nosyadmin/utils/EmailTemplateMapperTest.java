package com.nosy.admin.nosyadmin.utils;

import com.nosy.admin.nosyadmin.dto.EmailTemplateDto;
import com.nosy.admin.nosyadmin.dto.InputSystemDto;
import com.nosy.admin.nosyadmin.model.EmailFromProvider;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.InputSystem;
import jdk.internal.util.xml.impl.Input;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class EmailTemplateMapperTest {
    private EmailTemplateDto emailTemplateDto=new EmailTemplateDto();
    private EmailTemplate emailTemplate=new EmailTemplate();

    @Before
    public void setUp(){
        emailTemplate.setEmailTemplateName("EmailTemplateName");
        emailTemplate.setText("EmailTemplateText");
        emailTemplate.setEmailFromProvider(EmailFromProvider.DEFAULT);
        InputSystem inputSystem=new InputSystem();
        inputSystem.setInputSystemName("inputSystem");
        emailTemplate.setInputSystem(inputSystem);
        emailTemplate.setFromAddress("test@nosy.tech");
        emailTemplate.setPriority(1);
        emailTemplate.setRetryPeriod(1);
        emailTemplate.setSubject("EmailTemplateSubject");
        emailTemplate.setRetryTimes(1);
        String emailTemplateTo="emailTemplateTo";
        String emailTemplateCc="emailTemplateCc";
        Set<String> emailTemplateToSet=new HashSet<>();
        Set<String> emailTemplateCcSet=new HashSet<>();
        emailTemplateCcSet.add(emailTemplateCc);
        emailTemplateToSet.add(emailTemplateTo);
        emailTemplate.setEmailTemplateTo(emailTemplateToSet);
        emailTemplate.setEmailTemplateCc(emailTemplateCcSet);

    }
    @Test
    public void toEmailTemplateDto(){

        assertEquals(emailTemplate.getEmailFromProvider(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getEmailFromProvider());
        assertEquals(emailTemplate.getEmailTemplateCc(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getEmailTemplateCc());
        assertEquals(emailTemplate.getEmailTemplateId(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getEmailTemplateId());
        assertEquals(emailTemplate.getEmailTemplateTo(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getEmailTemplateTo());
        assertEquals(emailTemplate.getEmailTemplateName(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getEmailTemplateName());
        assertEquals(emailTemplate.getFromAddress(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getFromAddress());
        assertEquals(emailTemplate.getInputSystem(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getInputSystem());
        assertEquals(emailTemplate.getPriority(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getPriority());
        assertEquals(emailTemplate.getRetryPeriod(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getRetryPeriod());
        assertEquals(emailTemplate.getRetryTimes(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getRetryTimes());
        assertEquals(emailTemplate.getText(), EmailTemplateMapper.INSTANCE.toEmailTemplateDto(emailTemplate).getText());


    }

    @Test
    public void toEmailTemplate(){

    }

}
