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
    public void userAlreadyExistsException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.CONFLICT,restResponseEntityExceptionHandler.userAlreadyExistsException(new RuntimeException(), request).getStatusCode());
    }
    @Test
    public void emailTemplateNameInvalidException() {
        WebRequest request=mock(WebRequest.class);

        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.emailTemplateNameInvalidException(
                new RuntimeException(), request).getStatusCode());
    }



    @Test
    public void constraintViolationMustBeWellFormed() {
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
    public void constraintViolation() {
        WebRequest request=mock(WebRequest.class);
        //RollbackException rollbackException=new RollbackException();
        Throwable throwable=mock(Throwable.class);
        when(rollbackException.getCause()).thenReturn(throwable);
        when(throwable.getLocalizedMessage()).thenReturn("some fields cannot be determined");
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getStatusCode());
        assertEquals("Some fields cannot be determined. Please use appropriate format for all fields.",restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getBody() );

    }


    @Test
    public void passwordIsNotValidException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.passwordIsNotValid(new RuntimeException(), request).getStatusCode());
    }
    @Test
    public void authorizationServerCannotPerformTheOperation() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,restResponseEntityExceptionHandler.authorizationServerCannotPerformTheOperation(new RuntimeException(), request).getStatusCode());
    }

    @Test
    public void inputSystemExistAlreadyExistsException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.CONFLICT,restResponseEntityExceptionHandler.inputSystemExistAlreadyExistsException(new RuntimeException(), request).getStatusCode());

    }


    @Test
    public void inputSystemNameIsMandatoryException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.inputSystemNameIsMandatoryException(new RuntimeException(), request).getStatusCode());

    }

    @Test
    public void usernameAndPasswordAreNotProvidedForNonDefaultException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.usernameAndPasswordAreNotProvidedForNonDefaultException(new RuntimeException(), request).getStatusCode());

    }

    @Test
    public void invalidUsernameAndPasswordException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.invalidUsernameAndPasswordException(new RuntimeException(), request).getStatusCode());

    }

    @Test
    public void notAuthenticatedException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.UNAUTHORIZED,restResponseEntityExceptionHandler.notAuthenticatedException(new RuntimeException(), request).getStatusCode());

    }

    @Test
    public void emailTemplateNotFoundException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.NOT_FOUND,restResponseEntityExceptionHandler.emailTemplateNotFoundException(new RuntimeException(), request).getStatusCode());

    }

    @Test
    public void inputSystemNotFoundException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.NOT_FOUND,restResponseEntityExceptionHandler.inputSystemNotFoundException(new RuntimeException(), request).getStatusCode());

    }

    @Test
    public void emailTemplateExistException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.CONFLICT,restResponseEntityExceptionHandler.emailTemplateExistException(new RuntimeException(), request).getStatusCode());

    }

    @Test
    public void notEnoughParametersForPlaceholdersException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.notEnoughParametersForPlaceholdersException(new RuntimeException(), request).getStatusCode());

    }

    @Test
    public void inputSystemHasChildrenException() {
        WebRequest request=mock(WebRequest.class);
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.inputSystemHasChildrenException(new RuntimeException(), request).getStatusCode());

    }

    @Test(expected = InvalidUsernameAndPasswordException.class)
    public void InvalidUsernameAndPasswordException(){
        throw new InvalidUsernameAndPasswordException();
    }



}



