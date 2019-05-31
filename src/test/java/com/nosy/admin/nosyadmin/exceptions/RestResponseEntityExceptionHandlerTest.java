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
    public void constraintViolation() {
        WebRequest request=mock(WebRequest.class);
        //RollbackException rollbackException=new RollbackException();
        Throwable throwable=mock(Throwable.class);
        when(rollbackException.getCause()).thenReturn(throwable);
        when(throwable.getLocalizedMessage()).thenReturn("dasdasd");
        assertEquals(HttpStatus.BAD_REQUEST,restResponseEntityExceptionHandler.constraintViolation(rollbackException, request).getStatusCode());

    }
}
