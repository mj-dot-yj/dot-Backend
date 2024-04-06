package com.example.dot_backend.todo.entity;

import com.example.dot_backend.todo.enums.Priority;
import com.example.dot_backend.todo.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @Column(name="todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;
    private String title;
    private String content;
    private LocalTime start_time;
    private LocalTime end_time;
    private Long alarmed;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private State state;
    private LocalDate todo_date;


    public void updateTodo(String title, String content, Priority priority, LocalDate todo_date, Long alarmed, LocalTime start_time, LocalTime end_time) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.todo_date = todo_date;
        this.alarmed = alarmed;
        this.start_time = start_time;
        this.end_time = end_time;
        this.updated_date = LocalDateTime.now();
    }

    public void updateState(State state) {
        this.state = state;
    }

}
