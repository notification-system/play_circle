package com.nosy.admin.nosyadmin.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EmailCollection {
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Id
    @NotNull
    private String emailCollectionId;

    @NotNull private String emailCollectionName;

    @ElementCollection
    @JoinTable(name = "email_collection_emails", joinColumns = @JoinColumn(name = "email_collection_id"))
    @NotNull private List<String> emailCollectionEmails = new ArrayList<>();

    @NotNull private String inputSystemId;

    public EmailCollection() {}

    public String getEmailCollectionId() {
        return emailCollectionId;
    }

    public void setEmailCollectionId(String emailCollectionId) {
        this.emailCollectionId = emailCollectionId;
    }

    public String getEmailCollectionName() {
        return emailCollectionName;
    }

    public void setEmailCollectionName(String emailCollectionName) {
        this.emailCollectionName = emailCollectionName;
    }

    public List<String> getEmailCollectionEmails() {
        return emailCollectionEmails;
    }

    public void setEmailCollectionEmails(List<String> emailCollectionEmails) {
        this.emailCollectionEmails = emailCollectionEmails;
    }

    public String getInputSystemId() {
        return inputSystemId;
    }

    public void setInputSystemId(String inputSystemId) {
        this.inputSystemId = inputSystemId;
    }
}