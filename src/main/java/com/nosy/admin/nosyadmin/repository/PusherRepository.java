package com.nosy.admin.nosyadmin.repository;

import com.nosy.admin.nosyadmin.model.Pusher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PusherRepository extends JpaRepository<Pusher, String> {}
