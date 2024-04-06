package com.example.dot_backend.todo.dto;

import com.example.dot_backend.todo.enums.Priority;
import com.example.dot_backend.todo.entity.Todo;
import com.example.dot_backend.todo.enums.State;
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
    private Long user_id;
    private String title;
    private String content;
    private LocalTime start_time;
    private LocalTime end_time;
    private Long alarmed;
    private Priority priority;
    private LocalDate todo_date;

    public Todo toSaveTodo() {
        return Todo.builder()
                .user_id(this.user_id)
                .title(this.title)
                .content(this.content)
                .start_time(this.start_time)
                .end_time(this.end_time)
                .alarmed(this.alarmed)
                .priority(this.priority)
                .state(State.IN_PROGRESS)
                .todo_date(this.todo_date)
                .created_date(LocalDateTime.now())
                .build();
    }

    public TodoRequestDto(Long user_id, String title, String content, LocalTime start_time, LocalTime end_time, Long alarmed, Priority priority, LocalDate todo_date) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.start_time = start_time;
        this.end_time = end_time;
        this.alarmed = alarmed;
        this.priority = priority;
        this.todo_date = todo_date;
    }
}
