package com.nosy.admin.nosyadmin.repository;

import com.nosy.admin.nosyadmin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {}
