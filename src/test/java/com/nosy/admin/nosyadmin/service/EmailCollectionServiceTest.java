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

import java.util.*;

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
    private EmailCollectionEncoded emailCollectionEncoded;
    private String base64;
    private List<String> parsedList = new ArrayList<>();

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
        emailCollectionEncoded = new EmailCollectionEncoded();
        emailCollectionEncoded.setData("mocked data");
        emailCollectionEncoded.setName("email collection");
        base64 = "dGVzdDFAbWFpbC5jb20sdGVzdDJAbWFpbC5jb20=";
        parsedList.add("test1@mail.com");
        parsedList.add("test2@mail.com");
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
    public void updateEmailCollection() {
        when(emailCollectionRepository.findByEmailCollectionName(anyString())).thenReturn(emailCollection);
        doReturn(emailCollection).when(emailCollectionRepository).save(any());
        assertEquals(emailCollectionId, emailCollectionService.updateEmailCollection(emailCollectionEncoded).getEmailCollectionId());
    }

    @Test
    public void parseBase64Data() {
        assertEquals(parsedList, emailCollectionService.parseBase64Data(base64));
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