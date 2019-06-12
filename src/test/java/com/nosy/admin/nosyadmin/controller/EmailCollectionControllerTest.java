package com.nosy.admin.nosyadmin.controller;

import com.nosy.admin.nosyadmin.service.EmailCollectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EmailCollectionControllerTest {

    @InjectMocks
    EmailCollectionController emailCollectionController;

    @Mock
    EmailCollectionService emailCollectionService;

    @Test
    public void emailCollectionParse() {
        MockMultipartFile file = new MockMultipartFile("data", "filename.csv", "text/csv", "some csv".getBytes());
        assertEquals(HttpStatus.CREATED, emailCollectionController.
                uploadMultipart(file, "dasda", "dasda").getStatusCode());
    }

    @Test
    public void emailCollectionCreate() {
        List<String> emails = Collections.singletonList("dasda");
        assertEquals(HttpStatus.CREATED, emailCollectionController.
                createGroup(emails, "dasda", "dasda").getStatusCode());
    }

    @Test
    public void getAllEmailCollections() {
        assertEquals(HttpStatus.OK, emailCollectionController.
                getAllEmailCollections().getStatusCode());
    }

    @Test
    public void getAllEmailCollectionsBySystemId() {
        assertEquals(HttpStatus.OK, emailCollectionController.
                getAllEmailCollectionsByInputSystemId("dasda").getStatusCode());
    }

    @Test
    public void deleteEmailCollectionById() {
        assertEquals(HttpStatus.NO_CONTENT, emailCollectionController.
                deleteEmailCollectionById("emailCollectionId").getStatusCode());
    }
}
