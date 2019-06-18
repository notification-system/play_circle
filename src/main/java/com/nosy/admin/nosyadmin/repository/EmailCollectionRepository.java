package com.nosy.admin.nosyadmin.repository;

import com.nosy.admin.nosyadmin.model.EmailCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.transaction.Transactional;

@Transactional
@Repository
@CrossOrigin
public interface EmailCollectionRepository extends JpaRepository<EmailCollection, String> {
    EmailCollection findByEmailCollectionName(String name);
}
