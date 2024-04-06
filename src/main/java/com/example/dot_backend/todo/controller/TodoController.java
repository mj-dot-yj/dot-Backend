package com.example.dot_backend.todo.controller;

import com.example.dot_backend.common.ApiResponse;
import com.example.dot_backend.todo.dto.TodoRequestDto;
import com.example.dot_backend.todo.entity.Todo;
import com.example.dot_backend.todo.repository.TodoRepository;
import com.example.dot_backend.todo.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/saveTodo")
    public ResponseEntity<ApiResponse<Long>> saveTodo(@RequestBody @Valid TodoRequestDto todoRequestDto) {
        Long todoId = todoService.saveTodo(todoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.onSuccess(todoId));
    }

}
