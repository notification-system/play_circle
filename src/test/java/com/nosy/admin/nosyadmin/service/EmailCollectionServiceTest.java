package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.model.EmailCollection;
import com.nosy.admin.nosyadmin.repository.EmailCollectionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class EmailCollectionServiceTest {

    @InjectMocks
    private EmailCollectionService emailCollectionService;
    @Mock
    private EmailCollectionRepository emailCollectionRepository;
    private EmailCollection emailCollection;
    private String emailCollectionId;
    private String name;
    private List<String> emails = new ArrayList<>();
    private String inputSystemId;
    private List<EmailCollection> result = new ArrayList<>();

    private void setVariables() {
        emailCollectionId = "emailCollectionId";
        name = "NoSyGroup";
        emailCollection = new EmailCollection();
        inputSystemId = "inputSystemId";
        emails.addAll(Arrays.asList("darth.vader@deathstar.com", "luke.skywalker@tatooine.com"));
        emailCollection.setEmailCollectionName(name);
        emailCollection.setInputSystemId(inputSystemId);
        emailCollection.setEmailCollectionEmails(emails);
        emailCollection.setEmailCollectionId(emailCollectionId);
        result.clear();
        result.add(emailCollection);
    }

    @Before
    public void beforeEmailCollection() {
        setVariables();
    }

    @Test
    public void parseEmailCollection() {
        MockMultipartFile file = new MockMultipartFile("data", "filename.csv", "text/csv", "some csv".getBytes());
        doReturn(emailCollection).when(emailCollectionRepository).save(any());
        assertEquals(emailCollectionId, emailCollectionService.parseEmailCollection(file, inputSystemId, name).getEmailCollectionId());
    }

    @Test
    public void createEmailCollection() {
        doReturn(emailCollection).when(emailCollectionRepository).save(any());
        assertEquals(name, emailCollectionService.createEmailCollection(emails, inputSystemId, name).getEmailCollectionName());
    }

    @Test
    public void getAllEmailCollections() {
        doReturn(result).when(emailCollectionRepository).findAll();
        assertEquals(result, emailCollectionService.getAllEmailCollections());
    }

    @Test
    public void getAllEmailCollectionsByInputSystemId() {
        doReturn(result).when(emailCollectionRepository).getAllByInputSystemId(any());
        assertEquals(result, emailCollectionService.getAllEmailCollectionsByInputSystemId(inputSystemId));
    }

    @Test
    public void deleteEmailCollectionById() {
        doReturn(null).when(emailCollectionRepository).getAllByInputSystemId(inputSystemId);
        emailCollectionService.deleteEmailCollectionById(emailCollectionId);
        assertNull(emailCollectionRepository.getAllByInputSystemId(inputSystemId));
    }
}