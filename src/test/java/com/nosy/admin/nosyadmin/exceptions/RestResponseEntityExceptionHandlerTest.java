package com.nosy.admin.nosyadmin.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.RollbackException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class RestResponseEntityExceptionHandlerTest {
    @InjectMocks
    RestResponseEntityExceptionHandler restResponseEntityExceptionHandler;

    @Mock
    RollbackException rollbackException;

    @Test
    public void notEnoughParameterException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.CONFLICT,restResponseEntityExceptionHandler.notEnoughParameterException(new RuntimeException(), request).getStatusCode());
    }



    @Test
    public void constraintViolationMusrtBeWellFormed() {
        WebRequest request=mock(WebRequest.class);
        //RollbackException rollbackException=new RollbackException();
        Throwable throwable=mock(Throwable.class);
        when(rollbackException.getCause()).thenReturn(throwable);
        when(throwable.getLocalizedMessage()).thenReturn("must be a well-formed");
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getStatusCode());
        assertEquals("Email fields(Email To, Email From and Email CC) should be well-formed.",restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getBody() );

    }


    @Test
    public void constraintViolationMustNotBeNull() {
        WebRequest request=mock(WebRequest.class);
        //RollbackException rollbackException=new RollbackException();
        Throwable throwable=mock(Throwable.class);
        when(rollbackException.getCause()).thenReturn(throwable);
        when(throwable.getLocalizedMessage()).thenReturn("must not be null");
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getStatusCode());
        assertEquals("Please specify all Mandatory fields.",restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getBody() );
    }

    @Test
    public void constraintViolationMustNotBeEmpty() {
        WebRequest request=mock(WebRequest.class);
        //RollbackException rollbackException=new RollbackException();
        Throwable throwable=mock(Throwable.class);
        when(rollbackException.getCause()).thenReturn(throwable);
        when(throwable.getLocalizedMessage()).thenReturn("must not be empty");
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getStatusCode());
        assertEquals("Some fields cannot be empty: EmailTemplateName, EmailTo, EmailCc. However, EmailCc can be null but not empty.",restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getBody() );

    }

    @Test
    public void constraintViolationRest() {
        WebRequest request=mock(WebRequest.class);
        //RollbackException rollbackException=new RollbackException();
        Throwable throwable=mock(Throwable.class);
        when(rollbackException.getCause()).thenReturn(throwable);
        when(throwable.getLocalizedMessage()).thenReturn("some fields cannot be determined");
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getStatusCode());
        assertEquals("Some fields cannot be determined. Please use appropriate format for all fields.",restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getBody() );

    }
}


//    @ExceptionHandler(value = {ConstraintViolationException.class, RollbackException.class})
//  protected ResponseEntity<Object> constraintViolation(RollbackException ex, WebRequest request) {
//    String bodyOfError;
//    if (ex.getCause().getLocalizedMessage().contains("must be a well-formed")) {
//      bodyOfError = "Email fields(Email To, Email From and Email CC) should be well-formed";
//    } else if (ex.getCause().getLocalizedMessage().contains("must not be null")) {
//      bodyOfError = "Please specify all Mandatory fields.";
//    } else if (ex.getCause().getLocalizedMessage().contains("must not be empty")) {
//      bodyOfError =
//          "Some fields cannot be empty: EmailTemplateName, EmailTo, EmailCc. However, EmailCc can be null but not empty ";
//
//    } else {
//      bodyOfError =
//          "some fields cannot be determined. Please use appropriate format for all fields";
//    }
//
//    return handleExceptionInternal(
//        ex, bodyOfError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//  }
