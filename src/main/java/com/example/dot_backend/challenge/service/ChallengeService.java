package com.example.dot_backend.challenge.service;

import com.example.dot_backend.challenge.dto.ChallengeRequestDto;
import com.example.dot_backend.challenge.entity.Challenge;
import com.example.dot_backend.challenge.repository.ChallengeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Transactional
    public Long saveChallenge(ChallengeRequestDto challengeRequestDto) {
        Challenge challenge = challengeRepository.save(challengeRequestDto.toSaveChallenge());
        return challenge.getId();
    }
}
