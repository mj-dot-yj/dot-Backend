package com.example.dot_backend.todo.repository;


import com.example.dot_backend.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findTodoById(Long id);
    List<Todo> findAllByUserIdAndTodoDate(Long userId, LocalDate todoDate);
}
