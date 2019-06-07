package com.nosy.admin.nosyadmin.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(value = PasswordIsNotValidException.class)
  public ResponseEntity<Object> passwordIsNotValid(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.PASSWORD_IS_NOT_VALID.getMessageText(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = AuthorizationServerCannotPerformTheOperation.class)
  public ResponseEntity<Object> authorizationServerCannotPerformTheOperation(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.ACCESS_FORBIDDEN_EXCEPTION.getMessageText(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(value = UserAlreadyExistException.class)
  public ResponseEntity<Object> userAlreadyExistsException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.USER_ALREADY_EXISTS_EXCEPTION.getMessageText(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = InputSystemAlreadyExistsException.class)
  public ResponseEntity<Object> inputSystemExistAlreadyExistsException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.INPUT_SYSTEM_EXIST.getMessageText(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = EmailTemplateNameInvalidException.class)
  public ResponseEntity<Object> emailTemplateNameInvalidException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.EMAIL_TEMPLATE_NAME_CANNOT_BE_NULL.getMessageText(), new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
  }


  @ExceptionHandler(value = InputSystemNameIsMandatoryException.class)
  public ResponseEntity<Object> inputSystemNameIsMandatoryException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.INPUT_SYSTEM_NAME_IS_MANDATORY.getMessageText(), new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = UsernameAndPasswordAreNotProvidedForNonDefaultException.class)
  public ResponseEntity<Object> usernameAndPasswordAreNotProvidedForNonDefaultException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.USERNAME_AND_PASSWORD_ARE_REQUIRED_FOR_NON_DEFAULT.getMessageText(), new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
  }


  @ExceptionHandler(value = InvalidUsernameAndPasswordException.class)
  public ResponseEntity<Object> invalidUsernameAndPasswordException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.INVALID_USERNAME_OR_PASSWORD.getMessageText(), new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = NotAuthenticatedException.class)
  public ResponseEntity<Object> notAuthenticatedException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.NOT_AUTHENTICATED.getMessageText(), new HttpHeaders(),
            HttpStatus.UNAUTHORIZED, request);
  }

  @ExceptionHandler(value = EmailTemplateNotFoundException.class)
  public ResponseEntity<Object> emailTemplateNotFoundException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.NO_EMAIL_TEMPLATE_FOUND.getMessageText(), new HttpHeaders(),
            HttpStatus.NOT_FOUND, request);
  }
  @ExceptionHandler(value = InputSystemNotFoundException.class)
  public ResponseEntity<Object> inputSystemNotFoundException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.NO_INPUT_SYSTEM_FOUND.getMessageText(), new HttpHeaders(),
            HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler(value = EmailTemplateExistException.class)
  public ResponseEntity<Object> emailTemplateExistException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex, MessageError.EMAIL_TEMPLATE_EXIST.getMessageText(), new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler(value = NotEnoughParametersForPlaceholdersException.class)
  public ResponseEntity<Object> notEnoughParametersForPlaceholdersException(RuntimeException ex, WebRequest request) {

    return handleExceptionInternal(
            ex,  MessageError.NOT_ENOUGH_PARAMETERS_FOR_PLACEHOLDERS.getMessageText(), new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler(value = InputSystemHasChildrenException.class)
  public ResponseEntity<Object> inputSystemHasChildrenException(RuntimeException ex, WebRequest request) {
    return handleExceptionInternal(
            ex,  MessageError.INPUT_SYSTEM_HAS_CHILDREN.getMessageText(), new HttpHeaders(),
            HttpStatus.BAD_REQUEST, request);
  }


  @ExceptionHandler(value = {ConstraintViolationException.class, RollbackException.class})
  public ResponseEntity<Object> constraintViolation(RollbackException ex, WebRequest request) {
    String bodyOfError;
    if (ex.getCause().getLocalizedMessage().contains("must be a well-formed")) {
      bodyOfError = "Email fields(Email To, Email From and Email CC) should be well-formed.";
    } else if (ex.getCause().getLocalizedMessage().contains("must not be null")) {
      bodyOfError = "Please specify all Mandatory fields.";
    } else if (ex.getCause().getLocalizedMessage().contains("must not be empty")) {
      bodyOfError =
          "Some fields cannot be empty: EmailTemplateName, EmailTo, EmailCc. However, EmailCc can be null but not empty.";

    } else {
      bodyOfError =
          "Some fields cannot be determined. Please use appropriate format for all fields.";
    }

    return handleExceptionInternal(
        ex, bodyOfError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

}
