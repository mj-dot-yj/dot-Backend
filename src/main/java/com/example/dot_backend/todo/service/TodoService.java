package com.example.dot_backend.todo.service;

import com.example.dot_backend.todo.dto.TodoRequestDto;
import com.example.dot_backend.todo.entity.Todo;
import com.example.dot_backend.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public Long saveTodo(TodoRequestDto todoRequestDto) {
        Todo todo = todoRepository.save(todoRequestDto.toSaveTodo());
        return todo.getId();
    }
}
