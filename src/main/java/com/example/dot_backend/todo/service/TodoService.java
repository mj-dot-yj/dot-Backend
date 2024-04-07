package com.example.dot_backend.todo.service;

import com.example.dot_backend.todo.dto.TodoRequestDto;
import com.example.dot_backend.todo.dto.TodoResponseDto;
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

    public TodoResponseDto findTodoById(Long id) {
        Todo todo = todoRepository.findTodoById(id).orElseThrow(() -> new RuntimeException("no todo"));
        return todo.getTodoResponseDto();
    }

    @Transactional
    public void deleteTodo(Long id){
        Todo todoDeleted = todoRepository.findTodoById(id).orElseThrow(() -> new RuntimeException("no todo"));
        todoRepository.delete(todoDeleted);
    }
}
