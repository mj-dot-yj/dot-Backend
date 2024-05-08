package com.example.dot_backend.todo.dto;

import com.example.dot_backend.todo.enums.Priority;
import com.example.dot_backend.todo.entity.Todo;
import com.example.dot_backend.todo.enums.State;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Data
@Builder
public class TodoRequestDto {
    private Long userId;
    @NotBlank(message = "Enter your todo title")
    private String title;
    private String content;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long alarmed;
    private Priority priority;
    private LocalDate todoDate;
    private State state;

    public Todo toSaveTodo() {
        return Todo.builder()
                .userId(this.userId)
                .title(this.title)
                .content(this.content)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .alarmed(this.alarmed)
                .priority(this.priority)
                .state(State.IN_PROGRESS)
                .todoDate(this.todoDate)
                .createdDate(LocalDateTime.now())
                .build();
    }

    public TodoRequestDto(Long user_id, String title, String content, LocalTime start_time, LocalTime end_time, Long alarmed, Priority priority, LocalDate todo_date, State state) {
        this.userId = user_id;
        this.title = title;
        this.content = content;
        this.startTime = start_time;
        this.endTime = end_time;
        this.alarmed = alarmed;
        this.priority = priority;
        this.todoDate = todo_date;
        this.state = state;
    }
}
