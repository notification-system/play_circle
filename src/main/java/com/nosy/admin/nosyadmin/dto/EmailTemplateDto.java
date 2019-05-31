package com.nosy.admin.nosyadmin.dto;

import com.nosy.admin.nosyadmin.model.EmailFromProvider;
import com.nosy.admin.nosyadmin.model.InputSystem;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;


public class EmailTemplateDto {
  @NotNull private String emailTemplateId;

  @NotNull private String emailTemplateName;
  @NotNull @Email private String fromAddress;
  @NotNull private String subject;

  @NotNull private EmailFromProvider emailFromProvider;

  @NotNull private Set<@NotEmpty @Email String> emailTemplateTo;

  private Set<@NotEmpty @Email String> emailTemplateCc;

  @NotNull private String text;

  @NotNull private int retryTimes;

  private int retryPeriod;

  private int priority;
  @NotNull private InputSystem inputSystem;

  public String getEmailTemplateId() {
    return emailTemplateId;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public void setEmailTemplateId(String emailTemplateId) {
    this.emailTemplateId = emailTemplateId;
  }

  public String getEmailTemplateName() {
    return emailTemplateName;
  }

  public void setEmailTemplateName(String emailTemplateName) {
    this.emailTemplateName = emailTemplateName;
  }

  public String getFromAddress() {
    return fromAddress;
  }

  public void setFromAddress(String fromAddress) {
    this.fromAddress = fromAddress;
  }

  public EmailFromProvider getEmailFromProvider() {
    return emailFromProvider;
  }

  public void setEmailFromProvider(EmailFromProvider emailFromProvider) {
    this.emailFromProvider = emailFromProvider;
  }

  public Set<String> getEmailTemplateTo() {
    return emailTemplateTo;
  }

  public void setEmailTemplateTo(Set<String> emailTemplateTo) {
    this.emailTemplateTo = emailTemplateTo;
  }

  public Set<String> getEmailTemplateCc() {
    return emailTemplateCc;
  }

  public void setEmailTemplateCc(Set<String> emailTemplateCc) {
    this.emailTemplateCc = emailTemplateCc;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getRetryTimes() {
    return retryTimes;
  }

  public void setRetryTimes(int retryTimes) {
    this.retryTimes = retryTimes;
  }

  public int getRetryPeriod() {
    return retryPeriod;
  }

  public void setRetryPeriod(int retryPeriod) {
    this.retryPeriod = retryPeriod;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public InputSystem getInputSystem() {
    return inputSystem;
  }

  public void setInputSystem(InputSystem inputSystem) {
    this.inputSystem = inputSystem;
  }
}
