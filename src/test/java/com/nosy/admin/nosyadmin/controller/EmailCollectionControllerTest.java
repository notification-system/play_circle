package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.dto.EmailCollectionFileEncodedDto;
import com.nosy.admin.nosyadmin.service.EmailCollectionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class EmailCollectionControllerTest {

    @InjectMocks
    EmailCollectionController emailCollectionController;
    @Mock
    EmailCollectionService emailCollectionService;
    private EmailCollectionFileEncodedDto emailCollectionFileEncodedDto = new EmailCollectionFileEncodedDto();

    private void setVariables() {
        emailCollectionFileEncodedDto.setName("test");
        emailCollectionFileEncodedDto.setData("mock");
    }

    @Before
    public void beforeEmailCollectionController() {
        setVariables();
    }

    @Test
    public void emailCollectionUpload() {
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.CREATED, emailCollectionController.
                uploadEmailCollection(emailCollectionFileEncodedDto, principal).getStatusCode());
    }

    @Test
    public void emailCollectionCreate() {
        Principal principal=mock(Principal.class);
        assertEquals(HttpStatus.CREATED, emailCollectionController.
                createEmailCollection(emailCollectionFileEncodedDto, principal).getStatusCode());
    }

    @Test
    public void emailCollectionAddTo() {
        assertEquals(HttpStatus.OK, emailCollectionController.
                addToEmailCollection(emailCollectionFileEncodedDto).getStatusCode());
    }

    @Test
    public void emailCollectionReplace() {
        assertEquals(HttpStatus.OK, emailCollectionController.
                replaceEmailCollection(emailCollectionFileEncodedDto).getStatusCode());
    }

    @Test
    public void getAllEmailCollections() {
        assertEquals(HttpStatus.OK, emailCollectionController.
                getAllEmailCollections().getStatusCode());
    }

    @Test
    public void getEmailCollectionById() {
        assertEquals(HttpStatus.OK, emailCollectionController.
                getEmailCollectionById("emailCollectionId").getStatusCode());
    }

    @Test
    public void deleteEmailCollectionById() {
        assertEquals(HttpStatus.NO_CONTENT, emailCollectionController.
                deleteEmailCollectionById("emailCollectionId").getStatusCode());
    }
}
