package com.example.dot_backend.challenge.controller;

import com.example.dot_backend.challenge.dto.ChallengeRequestDto;
import com.example.dot_backend.challenge.dto.ChallengeResponseDto;
import com.example.dot_backend.challenge.service.ChallengeService;
import com.example.dot_backend.common.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/info/{idx}")
    public ResponseEntity<ApiResponse<ChallengeResponseDto>> findChallengeById(@PathVariable Long idx) {
        ChallengeResponseDto challengeResponseDto= challengeService.findChallengeById(idx);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(challengeResponseDto));
    }

    @GetMapping("/all/{idx}")
    public ResponseEntity<ApiResponse<List<ChallengeResponseDto>>> findAllByUserId(@PathVariable Long idx) {
        List<ChallengeResponseDto> challengeResponseDtoList = challengeService.findAllByUserId(idx);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.onSuccess(challengeResponseDtoList));
    }
}
