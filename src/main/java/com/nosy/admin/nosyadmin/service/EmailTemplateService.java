package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.exceptions.GeneralException;
import com.nosy.admin.nosyadmin.exceptions.MessageError;
import com.nosy.admin.nosyadmin.model.*;
import com.nosy.admin.nosyadmin.repository.EmailTemplateRepository;
import com.nosy.admin.nosyadmin.repository.InputSystemRepository;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmailTemplateService {

  private EmailTemplateRepository emailTemplateRepository;
  private Producer producer;
  private InputSystemRepository inputSystemRepository;
  private ReadyEmail readyEmail;
  private UserRepository userRepository;

  @Value("${default.nosy.from.address}")
  private String defaultNosyFromAddress;

  @Autowired
  public EmailTemplateService(
      EmailTemplateRepository emailTemplateRepository,
      Producer producer,
      InputSystemRepository inputSystemRepository,
      ReadyEmail readyEmail,
      UserRepository userRepository) {
    this.producer = producer;
    this.emailTemplateRepository = emailTemplateRepository;
    this.inputSystemRepository = inputSystemRepository;
    this.userRepository = userRepository;
    this.readyEmail = readyEmail;
  }

  public EmailTemplate getEmailTemplateById(
      String emailTemplateId, String inputSystemId, String email) {
    getInputSystemForTemplate(inputSystemId, email);
    EmailTemplate emailTemplate =
        emailTemplateRepository.findEmailTemplatesByInputSystemIdAndEmailTemplateId(
            inputSystemId, emailTemplateId);
    if (emailTemplate == null) {
      throw new GeneralException(MessageError.NO_EMAIL_TEMPLATE_FOUND.getMessageText());
    }

    return emailTemplate;
  }

  private InputSystem getInputSystemForTemplate(String inputSystemId, String email) {
    if (!checkUsername(email)) {
      throw new GeneralException(MessageError.NOT_AUTHENTICATED.getMessageText());
    }
    InputSystem inputSystem = inputSystemRepository.findByIdAndEmail(email, inputSystemId);
    if (inputSystem == null) {
      throw new GeneralException(MessageError.NO_INPUT_SYSTEM_FOUND.getMessageText());
    }
    return inputSystem;
  }

  public EmailTemplate newEmailTemplate(
      EmailTemplate emailTemplate, String inputSystemId, String email) {
    InputSystem inputSystem = getInputSystemForTemplate(inputSystemId, email);
    if (emailTemplateRepository.findEmailTemplateByEmailTemplateNameAndInputSystemId(
            emailTemplate.getEmailTemplateName(), inputSystemId)
        != null) {
      throw new GeneralException(MessageError.EMAIL_TEMPLATE_EXIST.getMessageText());
    }

    emailTemplate.setInputSystem(inputSystem);
    emailTemplateRepository.save(emailTemplate);

    return emailTemplate;
  }

  public List<String> getAllEmailProviders() {
    return Stream.of(EmailFromProvider.values())
        .map(EmailFromProvider::name)
        .collect(Collectors.toList());
  }

  public void deleteEmailTemplate(String inputSystemId, String emailTemplateId, String email) {
    EmailTemplate emailTemplate = getEmailTemplateById(emailTemplateId, inputSystemId, email);
    emailTemplateRepository.deleteById(emailTemplate.getEmailTemplateId());
  }

  public List<EmailTemplate> getListOfEmailTemplates(String inputSystemId, String email) {

    InputSystem inputSystem = inputSystemRepository.findByIdAndEmail(email, inputSystemId);
    if (inputSystem == null) {
      throw new GeneralException(MessageError.NO_INPUT_SYSTEM_FOUND.getMessageText());
    }

    return emailTemplateRepository.findEmailTemplatesByInputSystemId(inputSystemId);
  }

  public EmailTemplate postEmailTemplate(
      String inputSystemId,
      String emailTemplateId,
      EmailProviderProperties emailProviderProperties,
      String email) {

    EmailTemplate emailTemplate = getEmailTemplateById(emailTemplateId, inputSystemId, email);

    boolean auth =
        (emailProviderProperties.getUsername() == null
            || emailProviderProperties.getUsername().equals("")
            || emailProviderProperties.getPassword() == null
            || emailProviderProperties.getPassword().equals(""));

    if (!emailTemplate.getEmailFromProvider().equals(EmailFromProvider.DEFAULT) && auth) {
      throw new GeneralException(
          MessageError.USERNAME_AND_PASSWORD_ARE_REQUIRED_FOR_NON_DEFAULT.getMessageText());
    }

    String text = emailTemplate.getText();

    for (PlaceHolders placeholder : emailProviderProperties.getPlaceholders()) {
      text = text.replace("#{" + placeholder.getName() + "}#", placeholder.getValue());
    }

    if (text.contains("#{") || text.contains("}#")) {
      throw new GeneralException(
          MessageError.NOT_ENOUGH_PARAMETERS_FOR_PLACEHOLDERS.getMessageText());
    }
    emailTemplate.setText(text);
    readyEmail.setEmailTemplate(emailTemplate);
    readyEmail.setEmailProviderProperties(emailProviderProperties);
    producer.sendMessage(readyEmail.toString());
    return emailTemplate;
  }

  public EmailTemplate updateEmailTemplate(
      EmailTemplate emailTemplate, String inputSystemId, String emailTemplateId, String email) {

    EmailTemplate currentEmailTemplate =
        getEmailTemplateById(emailTemplateId, inputSystemId, email);

    if (!currentEmailTemplate.getEmailTemplateName().equals(emailTemplate.getEmailTemplateName())
        && emailTemplateRepository.findEmailTemplateByEmailTemplateNameAndInputSystemId(
                emailTemplate.getEmailTemplateName(), inputSystemId)
            != null) {

      throw new GeneralException(MessageError.NO_EMAIL_TEMPLATE_FOUND.getMessageText());
    }
    if (emailTemplate.getEmailTemplateName() == null
        || emailTemplate.getEmailTemplateName().isEmpty()) {
      throw new GeneralException(MessageError.EMAIL_TEMPLATE_CANNOT_BE_NULL.getMessageText());
    }

    if (emailTemplate.getFromAddress() == null && emailTemplate.getFromAddress().isEmpty()) {

      currentEmailTemplate.setFromAddress(defaultNosyFromAddress);
    }

    currentEmailTemplate.setEmailTemplateName(emailTemplate.getEmailTemplateName());

    currentEmailTemplate.setEmailTemplateTo(emailTemplate.getEmailTemplateTo());
    currentEmailTemplate.setFromAddress(emailTemplate.getFromAddress());
    currentEmailTemplate.setText(emailTemplate.getText());
    currentEmailTemplate.setEmailFromProvider(emailTemplate.getEmailFromProvider());
    currentEmailTemplate.setEmailTemplateCc(emailTemplate.getEmailTemplateCc());
    currentEmailTemplate.setPriority(emailTemplate.getPriority());
    currentEmailTemplate.setRetryPeriod(emailTemplate.getRetryPeriod());
    currentEmailTemplate.setRetryTimes(emailTemplate.getRetryTimes());
    currentEmailTemplate.setSubject(emailTemplate.getSubject());
    emailTemplateRepository.save(currentEmailTemplate);
    return currentEmailTemplate;
  }

  private boolean checkUsername(String email) {

    return userRepository.findById(email).isPresent();
  }
}
