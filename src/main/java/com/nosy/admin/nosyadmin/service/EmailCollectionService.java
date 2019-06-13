package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.exceptions.UserNotExistsException;
import com.nosy.admin.nosyadmin.model.EmailCollection;
import com.nosy.admin.nosyadmin.model.User;
import com.nosy.admin.nosyadmin.repository.EmailCollectionRepository;
import com.nosy.admin.nosyadmin.repository.UserRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EmailCollectionService {
    private EmailCollectionRepository emailCollectionRepository;
    private UserRepository userRepository;

    @Autowired
    public EmailCollectionService(
            EmailCollectionRepository emailCollectionRepository,
            UserRepository userRepository) {
        this.emailCollectionRepository = emailCollectionRepository;
        this.userRepository = userRepository;
    }

    public EmailCollection parseEmailCollection(String file, String name, String email) {
        EmailCollection emailCollection = new EmailCollection();
        emailCollection.setEmailCollectionName(name);

        Optional<User> user = userRepository.findById(email);
        if (!user.isPresent()) {
            throw new UserNotExistsException();
        }
        emailCollection.setUser(user.get());

        byte[] bytes = Base64.decodeBase64(file);
        String completeData = new String(bytes);
        String[] emails = completeData.split(",");
        for (int i = 0; i < emails.length; i++) {
            emails[i] = emails[i].trim();
        }
        emailCollection.getEmailCollectionEmails().addAll(Arrays.asList(emails));

        return emailCollectionRepository.save(emailCollection);
    }

    public EmailCollection createEmailCollection(List<String> emails, String name, String email) {
        EmailCollection emailCollection = new EmailCollection();
        emailCollection.setEmailCollectionName(name);
        emailCollection.setEmailCollectionEmails(emails);

        Optional<User> user = userRepository.findById(email);
        if (!user.isPresent()) {
            throw new UserNotExistsException();
        }
        emailCollection.setUser(user.get());

        return emailCollectionRepository.save(emailCollection);
    }

    public EmailCollection getEmailCollectionById(String emailCollectionId) {
        if(emailCollectionRepository.findById(emailCollectionId).isPresent()) {
            return emailCollectionRepository.findById(emailCollectionId).get();
        } else {
            return null;
        }
    }

    public List<EmailCollection> getAllEmailCollections() {
        return emailCollectionRepository.findAll();
    }

    public void deleteEmailCollectionById(String emailCollectionId) {
        emailCollectionRepository.deleteById(emailCollectionId);
    }
}
