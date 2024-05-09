package com.example.dot_backend.todo.service;

import com.example.dot_backend.todo.dto.TodoRequestDto;
import com.example.dot_backend.todo.dto.TodoResponseDto;
import com.example.dot_backend.todo.entity.Todo;
import com.example.dot_backend.todo.enums.State;
import com.example.dot_backend.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public Long modifyTodo(Long id, TodoRequestDto todoRequestDto) {
        Todo todo = todoRepository.findTodoById(id).orElseThrow(() -> new RuntimeException("no todo"));
        todo.updateTodo(todoRequestDto);
        return todo.getId();
    }

    @Transactional
    public Long modifyTodoState(Long id, State state) {
        Todo todo = todoRepository.findTodoById(id).orElseThrow(() -> new RuntimeException("no todo"));
        todo.updateState(state);
        return todo.getId();
    }

    @Transactional
    public List<TodoResponseDto> findAllTodoByUserIdAndTodoDate(Long userId, LocalDate todoDate) {
        List<Todo> todoList = todoRepository.findAllByUserIdAndTodoDate(userId, todoDate);
        List<TodoResponseDto> todoResponseDtoList = new ArrayList<>();
        for(Todo todo : todoList) {
            todoResponseDtoList.add(todo.getTodoResponseDto());
        }
        return todoResponseDtoList;
    }
}
