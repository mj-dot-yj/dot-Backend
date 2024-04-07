package com.example.dot_backend.todo.dto;

import com.example.dot_backend.todo.enums.Priority;
import com.example.dot_backend.todo.enums.State;
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
    private Long user_id;
    private String title;
    private String content;
    private LocalTime start_time;
    private LocalTime end_time;
    private Long alarmed;
    private Priority priority;
    private State state;
    private LocalDate todo_date;
}
