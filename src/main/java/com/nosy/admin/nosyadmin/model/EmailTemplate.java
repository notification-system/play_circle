package com.nosy.admin.nosyadmin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@DynamicInsert
@ToString
@NoArgsConstructor
@Table(
    name = "emailTemplate",
    uniqueConstraints = @UniqueConstraint(columnNames = {"emailTemplateName", "input_system_id"}))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EmailTemplate {
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Id
  @NotNull
  private String emailTemplateId;

  @NotNull private String emailTemplateName;
  @NotNull @Email private String fromAddress;

  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "text default 'Default'")
  @NotNull
  private EmailFromProvider emailFromProvider;

  @NotNull
  @ElementCollection
  @JoinTable(name = "email_template_to", joinColumns = @JoinColumn(name = "email_template_id"))
  private Set<@NotEmpty @Email String> emailTemplateTo;

  @ElementCollection
  @JoinTable(name = "email_template_cc", joinColumns = @JoinColumn(name = "email_template_id"))
  private Set<@NotEmpty @Email String> emailTemplateCc;

  @NotNull private String text;

  @NotNull
  @Column(columnDefinition = "int default 0")
  private int retryTimes;

  private int retryPeriod;

  private int priority;

  @NotNull
  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "input_system_id")
  private InputSystem inputSystem;

  @NotNull private String subject;

  @PrePersist
  protected void onCreate() {
    if (emailTemplateName == null || emailTemplateName.isEmpty()) {
      throw new GeneralException("Email Template cannot be empty or null");
    }
  }

  public EmailFromProvider getEmailFromProvider() {
    return emailFromProvider;
  }

  public void setEmailFromProvider(EmailFromProvider emailFromProvider) {
    this.emailFromProvider = emailFromProvider;
  }

  public String getEmailTemplateId() {
    return emailTemplateId;
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

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  @Override
  public String toString() {
    return "EmailTemplate{"
        + "emailTemplateId='"
        + emailTemplateId
        + '\''
        + ", emailTemplateName='"
        + emailTemplateName
        + '\''
        + ", fromAddress='"
        + fromAddress
        + '\''
        + ", emailTemplateTo="
        + emailTemplateTo
        + ", emailTemplateCc="
        + emailTemplateCc
        + ", text='"
        + text
        + '\''
        + ", retryTimes="
        + retryTimes
        + ", retryPeriod="
        + retryPeriod
        + ", priority="
        + priority
        + ", subject='"
        + subject
        + '\''
        + '}';
  }
}
