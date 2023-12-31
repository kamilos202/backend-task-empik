package com.example.backendtaskempik.repository;

import com.example.backendtaskempik.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JpaRepository for handling Request entities.
 */
public interface RequestRepository extends JpaRepository<Request, String> {
    Optional<Request> findByLogin(String login);
}
