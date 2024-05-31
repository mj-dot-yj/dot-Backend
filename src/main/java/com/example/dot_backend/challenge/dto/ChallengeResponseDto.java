package com.example.dot_backend.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@Getter
public class ChallengeResponseDto {
    private Long id;
    private Long userId;
    private String title;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH-mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH-mm")
    private LocalTime endTime;
    private Long alarmed;
    private Long count;
    private Long totalCount;
    private List<String> days;
}
