package com.example.dot_backend.todo.repository;


import com.example.dot_backend.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findTodoById(Long id);
    List<Todo> findAllByUserId(Long userId);
}
