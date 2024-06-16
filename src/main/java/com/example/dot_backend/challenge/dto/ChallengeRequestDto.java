package com.example.dot_backend.challenge.dto;

import com.example.dot_backend.challenge.entity.Challenge;
import com.example.dot_backend.challenge.enums.State;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    private List<String> days;

    public Challenge toSaveChallenge() {
        return Challenge.builder()
                .userId(this.userId)
                .title(this.title)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .alarmed(this.alarmed)
                .count(0L)
                .totalCount(this.totalCount)
                .days(this.days)
                .createdDate(LocalDateTime.now())
                .checked(State.NOT_STARTED)
                .build();
    }

    public ChallengeRequestDto(Long userId, String title, LocalTime startTime, LocalTime endTime, Long alarmed, Long totalCount, List<String> days) {
        this.userId = userId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.alarmed = alarmed;
        this.totalCount = totalCount;
        this.days = days;
    }
}
