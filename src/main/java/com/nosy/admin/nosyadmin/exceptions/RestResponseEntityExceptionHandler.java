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
    @ExceptionHandler(value = {GeneralException.class})

    protected ResponseEntity<Object> notEnoughParameterException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, RollbackException.class})
    protected ResponseEntity<Object> constraintViolation(RollbackException ex, WebRequest request) {
        String bodyOfError;
        if (ex.getCause().getLocalizedMessage().contains("must be a well-formed")) {
            bodyOfError = "Email fields(Email To, Email From and Email CC) should be well-formed";
        } else if (ex.getCause().getLocalizedMessage().contains("must not be null")) {
            bodyOfError = "Please specify all Mandatory fields.";
        } else if (ex.getCause().getLocalizedMessage().contains("must not be empty")) {
            bodyOfError = "Some fields cannot be empty: EmailTemplateName, EmailTo, EmailCc. However, EmailCc can be null but not empty ";

        } else {
            bodyOfError = "some fields cannot be determined. Please use appropriate format for all fields";

        }

        return handleExceptionInternal(ex, bodyOfError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
