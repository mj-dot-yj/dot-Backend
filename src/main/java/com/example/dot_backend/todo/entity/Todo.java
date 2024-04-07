package com.example.dot_backend.todo.entity;

import com.example.dot_backend.todo.dto.TodoRequestDto;
import com.example.dot_backend.todo.dto.TodoResponseDto;
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

    private Long userId;
    private String title;
    private String content;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long alarmed;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private State state;
    private LocalDate todoDate;


    public void updateTodo(TodoRequestDto todoRequestDto) {
        this.title = todoRequestDto.getTitle();
        this.content = todoRequestDto.getContent();
        this.priority = todoRequestDto.getPriority();
        this.todoDate = todoRequestDto.getTodoDate();
        this.alarmed = todoRequestDto.getAlarmed();
        this.startTime = todoRequestDto.getStartTime();
        this.endTime = todoRequestDto.getEndTime();
        this.updatedDate = LocalDateTime.now();
    }

    public void updateState(State state) {
        this.state = state;
    }

    public TodoResponseDto getTodoResponseDto() {
        return TodoResponseDto.builder()
                .id(this.id)
                .userId(this.userId)
                .title(this.title)
                .content(this.content)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .alarmed(this.alarmed)
                .priority(this.priority)
                .state(this.state)
                .todoDate(this.todoDate)
                .build();
    }
}
