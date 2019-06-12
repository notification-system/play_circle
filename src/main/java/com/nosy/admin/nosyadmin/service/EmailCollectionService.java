package com.nosy.admin.nosyadmin.service;

import com.nosy.admin.nosyadmin.model.EmailCollection;
import com.nosy.admin.nosyadmin.repository.EmailCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class EmailCollectionService {
    private EmailCollectionRepository emailCollectionRepository;

    @Autowired
    public EmailCollectionService(
            EmailCollectionRepository emailCollectionRepository) {
        this.emailCollectionRepository = emailCollectionRepository;
    }

    public EmailCollection parseEmailCollection(MultipartFile file, String inputSystemId, String name) {
        EmailCollection emailCollection = new EmailCollection();
        emailCollection.setInputSystemId(inputSystemId);
        emailCollection.setEmailCollectionName(name);

        try {
            byte[] bytes = file.getBytes();
            String completeData = new String(bytes);
            String[] emails = completeData.split(",");
            for (int i = 0; i < emails.length; i++) {
                emails[i] = emails[i].trim();
            }
            emailCollection.getEmailCollectionEmails().addAll(Arrays.asList(emails));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emailCollectionRepository.save(emailCollection);
    }

    public EmailCollection createEmailCollection(List<String> emails, String inputSystemId, String name) {
        EmailCollection emailCollection = new EmailCollection();
        emailCollection.setInputSystemId(inputSystemId);
        emailCollection.setEmailCollectionName(name);
        emailCollection.setEmailCollectionEmails(emails);
        return emailCollectionRepository.save(emailCollection);
    }

    public List<EmailCollection> getAllEmailCollectionsByInputSystemId(String inputSystemId) {
        return emailCollectionRepository.getAllByInputSystemId(inputSystemId);
    }

    public List<EmailCollection> getAllEmailCollections() {
        return emailCollectionRepository.findAll();
    }

    public void deleteEmailCollectionById(String emailCollectionId) {
        emailCollectionRepository.deleteById(emailCollectionId);
    }
}
