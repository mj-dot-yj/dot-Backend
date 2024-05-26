package com.example.dot_backend.challenge.dto;

import com.example.dot_backend.challenge.entity.Challenge;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Data
@Builder
public class ChallengeRequestDto {
    private Long userId;
    private String title;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long alarmed;
    private Long totalCount;
    private String period;

    public Challenge toSaveChallenge() {
        return Challenge.builder()
                .userId(this.userId)
                .title(this.title)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .alarmed(this.alarmed)
                .count(0L)
                .totalCount(this.totalCount)
                .period(this.period)
                .createdDate(LocalDateTime.now())
                .build();
    }

    public ChallengeRequestDto(Long userId, String title, LocalTime startTime, LocalTime endTime, Long alarmed, Long totalCount, String period) {
        this.userId = userId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.alarmed = alarmed;
        this.totalCount = totalCount;
        this.period = period;
    }
}
