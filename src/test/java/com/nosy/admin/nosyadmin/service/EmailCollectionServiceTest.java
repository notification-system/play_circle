package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.dto.EmailCollectionEncoded;
import com.nosy.admin.nosyadmin.model.EmailCollection;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.repository.EmailCollectionRepository;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmailCollectionServiceTest {

    @InjectMocks
    private EmailCollectionService emailCollectionService;
    @Mock
    private EmailCollectionRepository emailCollectionRepository;
    @Mock
    private UserRepository userRepository;
    private EmailCollection emailCollection;
    private String emailCollectionId;
    private String name;
    private List<String> emails = new ArrayList<>();
    private List<EmailCollection> result = new ArrayList<>();
    private User user;
    private String email;
    private EmailCollectionEncoded emailCollectionEncoded = new EmailCollectionEncoded();

    private void setVariables() {
        emailCollectionId = "emailCollectionId";
        name = "NoSyGroup";
        email = "test@nosy.tech";
        emailCollection = new EmailCollection();
        emails.addAll(Arrays.asList("darth.vader@deathstar.com", "luke.skywalker@tatooine.com"));
        emailCollection.setEmailCollectionName(name);
        emailCollection.setEmailCollectionEmails(emails);
        emailCollection.setEmailCollectionId(emailCollectionId);
        result.clear();
        result.add(emailCollection);
        user=new User();
        user.setEmail(email);
        user.setFirstName("Test");
        user.setLastName("Nosy");
        user.setInfo("TestNosy");
        user.setPassword("dajsndjasn");
        emailCollectionEncoded.setData("mockedData");
        emailCollectionEncoded.setName("email collection");
    }

    @Before
    public void beforeEmailCollection() {
        setVariables();
    }

    @Test
    public void parseEmailCollection() {
        when(userRepository.findById(email)).thenReturn(Optional.of(user));
        doReturn(emailCollection).when(emailCollectionRepository).save(any());
        assertEquals(emailCollectionId, emailCollectionService.parseEmailCollection(emailCollectionEncoded, user.getEmail()).getEmailCollectionId());
    }

    @Test
    public void createEmailCollection() {
        when(userRepository.findById(email)).thenReturn(Optional.of(user));
        doReturn(emailCollection).when(emailCollectionRepository).save(any());
        assertEquals(name, emailCollectionService.createEmailCollection(emails, name, user.getEmail()).getEmailCollectionName());
    }

    @Test
    public void getAllEmailCollections() {
        doReturn(result).when(emailCollectionRepository).findAll();
        assertEquals(result, emailCollectionService.getAllEmailCollections());
    }

    @Test
    public void getEmailCollectionById() {
        doReturn(Optional.of(emailCollection)).when(emailCollectionRepository).findById(anyString());
        assertEquals(emailCollection, emailCollectionService.getEmailCollectionById(emailCollectionId));
    }

    @Test
    public void deleteEmailCollectionById() {
        doReturn(null).when(emailCollectionRepository).findById(anyString());
        emailCollectionService.deleteEmailCollectionById(emailCollectionId);
        assertNotEquals(emailCollection, emailCollectionRepository.findById(emailCollectionId));
    }
}