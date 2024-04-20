package com.example.dot_backend.todo.dto;

import com.example.dot_backend.todo.enums.Priority;
import com.example.dot_backend.todo.enums.State;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Data
@Builder
public class TodoResponseDto {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH-mm")
    private LocalTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH-mm")
    private LocalTime endTime;
    private Long alarmed;
    private Priority priority;
    private State state;
    private LocalDate todoDate;
}
