package com.nosy.admin.nosyadmin.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nosy.admin.nosyadmin.model.EmailTemplate;
import com.nosy.admin.nosyadmin.model.User;

import javax.validation.constraints.NotNull;
import java.util.Set;


public class InputSystemDto {
  @NotNull private String inputSystemId;
  @NotNull private String inputSystemName;
  @JsonBackReference private User user;

  @JsonManagedReference private Set<EmailTemplate> emailTemplate;

  public String getInputSystemId() {
    return inputSystemId;
  }

  public void setInputSystemId(String inputSystemId) {
    this.inputSystemId = inputSystemId;
  }

  public String getInputSystemName() {
    return inputSystemName;
  }

  public void setInputSystemName(String inputSystemName) {
    this.inputSystemName = inputSystemName;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<EmailTemplate> getEmailTemplate() {
    return emailTemplate;
  }

  public void setEmailTemplate(Set<EmailTemplate> emailTemplate) {
    this.emailTemplate = emailTemplate;
  }
}
