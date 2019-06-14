package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.dto.EmailCollectionEncoded;
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

    public EmailCollection parseEmailCollection(EmailCollectionEncoded emailCollectionEncoded, String email) {
        EmailCollection emailCollection = new EmailCollection();
        emailCollection.setEmailCollectionName(emailCollectionEncoded.getName());

        Optional<User> user = userRepository.findById(email);
        if (!user.isPresent()) {
            throw new UserNotExistsException();
        }
        emailCollection.setUser(user.get());

        List<String> emails = parseBase64Data(emailCollectionEncoded.getData());
        emailCollection.getEmailCollectionEmails().addAll(emails);

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

    public EmailCollection updateEmailCollection(EmailCollectionEncoded emailCollectionEncoded) {
        EmailCollection emailCollection = emailCollectionRepository.findByEmailCollectionName(emailCollectionEncoded.getName());
        List<String> emails = parseBase64Data(emailCollectionEncoded.getData());
        emails.forEach(e -> emailCollection.getEmailCollectionEmails().add(e));

        return emailCollectionRepository.save(emailCollection);
    }

    List<String> parseBase64Data(String data) {
        byte[] bytes = Base64.decodeBase64(data);
        String completeData = new String(bytes);
        String[] emails = completeData.split(",");
        for (int i = 0; i < emails.length; i++) {
            emails[i] = emails[i].trim();
        }
        return Arrays.asList(emails);
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
