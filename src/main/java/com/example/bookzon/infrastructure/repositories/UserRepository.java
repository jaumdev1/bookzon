package com.example.bookzon.infrastructure.repositories;

import com.example.bookzon.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
  UserDetails findByUsername(String login);
}
