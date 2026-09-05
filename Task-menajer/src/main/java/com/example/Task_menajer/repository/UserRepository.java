package com.example.Task_menajer.repository;

import com.example.Task_menajer.domain.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findUsersByEmail(String email);
}
