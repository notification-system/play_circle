package com.nosy.admin.nosyadmin.exceptions;

import com.nosy.admin.nosyadmin.service.KeycloakService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
  private static final Logger logger = LoggerFactory.getLogger(KeycloakService.class);


  @ExceptionHandler(value = PasswordIsNotValidException.class)
  public ResponseEntity<MessageError> passwordIsNotValid(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.PASSWORD_IS_NOT_VALID, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = AuthorizationServerCannotPerformTheOperation.class)
  public ResponseEntity<MessageError> authorizationServerCannotPerformTheOperation(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.ACCESS_FORBIDDEN_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = UserAlreadyExistException.class)
  public ResponseEntity<MessageError> userAlreadyExistsException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.USER_ALREADY_EXISTS_EXCEPTION, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = InputSystemAlreadyExistsException.class)
  public ResponseEntity<MessageError> inputSystemExistAlreadyExistsException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.INPUT_SYSTEM_EXIST, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = EmailTemplateNameInvalidException.class)
  public ResponseEntity<MessageError> emailTemplateNameInvalidException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.EMAIL_TEMPLATE_NAME_CANNOT_BE_NULL, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(value = InputSystemNameIsMandatoryException.class)
  public ResponseEntity<MessageError> inputSystemNameIsMandatoryException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.INPUT_SYSTEM_NAME_IS_MANDATORY, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = UsernameAndPasswordAreNotProvidedForNonDefaultException.class)
  public ResponseEntity<MessageError> usernameAndPasswordAreNotProvidedForNonDefaultException(RuntimeException ex, WebRequest request) {
    return new ResponseEntity<>(MessageError.USERNAME_AND_PASSWORD_ARE_REQUIRED_FOR_NON_DEFAULT, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(value = InvalidUsernameAndPasswordException.class)
  public ResponseEntity<MessageError> invalidUsernameAndPasswordException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.INVALID_USERNAME_OR_PASSWORD, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = NotAuthenticatedException.class)
  public ResponseEntity<MessageError> notAuthenticatedException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.NOT_AUTHENTICATED, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = EmailTemplateNotFoundException.class)
  public ResponseEntity<MessageError> emailTemplateNotFoundException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.NO_EMAIL_TEMPLATE_FOUND, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(value = InputSystemNotFoundException.class)
  public ResponseEntity<MessageError> inputSystemNotFoundException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.NO_INPUT_SYSTEM_FOUND, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = EmailTemplateExistException.class)
  public ResponseEntity<MessageError> emailTemplateExistException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.EMAIL_TEMPLATE_EXIST, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = NotEnoughParametersForPlaceholdersException.class)
  public ResponseEntity<MessageError> notEnoughParametersForPlaceholdersException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.NOT_ENOUGH_PARAMETERS_FOR_PLACEHOLDERS, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = InputSystemHasChildrenException.class)
  public ResponseEntity<MessageError> inputSystemHasChildrenException(RuntimeException ex, WebRequest request) {
    logger.error(ex.toString());
    logger.error(request.toString());
    return new ResponseEntity<>(MessageError.INPUT_SYSTEM_HAS_CHILDREN, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(value = {ConstraintViolationException.class, RollbackException.class})
  public ResponseEntity<MessageError> constraintViolation(RollbackException ex, WebRequest request) {
    logger.error(request.toString());
    if (ex.getCause().getLocalizedMessage().contains("must be a well-formed")) {
      return new ResponseEntity<>(MessageError.EMAIL_FIELDS_SHOULD_BE_WELL_FORMED, HttpStatus.BAD_REQUEST);
    } else if (ex.getCause().getLocalizedMessage().contains("must not be null") ||
            ex.getCause().getLocalizedMessage().contains("must not be empty")) {

      return new ResponseEntity<>(MessageError.NOT_ALL_MANDATORY_FIELDS_SPECIFIED, HttpStatus.BAD_REQUEST);
    } else {
      return new ResponseEntity<>(MessageError.FIELDS_CANNOT_BE_DETERMINED, HttpStatus.BAD_REQUEST);
    }
  }

}
