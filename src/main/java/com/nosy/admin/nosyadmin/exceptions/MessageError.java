package com.nosy.admin.nosyadmin.exceptions;

public enum MessageError {
  NOT_AUTHENTICATED("You are not authenticated. Please login first"),
  NO_INPUT_SYSTEM_FOUND(
      "No Input System with specified Id was found. Please create it before updating"),
  NO_EMAIL_TEMPLATE_FOUND(
      "No Template was found with specified request. Please correct your request"),
  EMAIL_TEMPLATE_EXIST("Email Template already exists. Please try another name"),
  EMAIL_TEMPLATE_CANNOT_BE_NULL("Email Template Name cannot be null or empty"),
  USERNAME_AND_PASSWORD_ARE_REQUIRED_FOR_NON_DEFAULT(
      "Username and password are required for non-Default Email Provider"),
  NOT_ENOUGH_PARAMETERS_FOR_PLACEHOLDERS(
      "Not enough paramaters to replace. Please add all placeholders"),
  INPUT_SYSTEM_HAS_CHILDREN(
      "Input System cannot be deleted. Because it has dependent children. Please delete Email Templates associated with this InputSystem to be able to delete it"),
  INPUT_SYSTEM_EXIST(
      "InputSystem with current name already exists in the system please. Please try another name"),
  USER_DOES_NOT_EXIST("User does not exists please register first"),
  INPUT_SYSTEM_NAME_IS_MANDATORY("Input System Name is mandatory field"),
  PASSWORD_IS_NOT_VALID("Password is not valid"),
  ACCESS_FORBIDDEN_EXCEPTION("Authorization server is not responding, please try again later"),
  USER_ALREADY_EXISTS_EXCEPTION("User already exists in database please try another email");

  private String messageText;

  MessageError(String message) {
    this.messageText = message;
  }

  public String getMessageText() {
    return messageText;
  }
}
