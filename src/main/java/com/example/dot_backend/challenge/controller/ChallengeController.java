package com.example.dot_backend.challenge.controller;

import com.example.dot_backend.challenge.dto.ChallengeRequestDto;
import com.example.dot_backend.challenge.service.ChallengeService;
import com.example.dot_backend.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping("/saveChallenge")
    public ResponseEntity<ApiResponse<Long>> saveChallenge(@RequestBody @Valid ChallengeRequestDto challengeRequestDto) {
        Long challengeId = challengeService.saveChallenge(challengeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(challengeId));
    }
}
