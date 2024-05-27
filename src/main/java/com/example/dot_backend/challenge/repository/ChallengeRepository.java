package com.example.dot_backend.challenge.repository;

import com.example.dot_backend.challenge.entity.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Optional<Challenge> findChallengeById(Long id);
    List<Challenge> findAllByUserId(Long id);
}
