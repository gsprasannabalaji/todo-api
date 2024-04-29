package com.todos.todos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todos.todos.entities.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long> {
}
