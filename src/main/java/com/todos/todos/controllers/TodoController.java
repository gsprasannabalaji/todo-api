package com.todos.todos.controllers;

import com.todos.todos.controllers.TodoDto.TodoDto;
import com.todos.todos.entities.Todo;
import com.todos.todos.repositories.TodoRepo;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TodoController {

   @Autowired
   public TodoRepo todoRepo;

   @GetMapping("/todos")
   public List<Todo> root() {
       return this.todoRepo.findAll();
   }

   @PostMapping("/todos")
   public ResponseEntity<Object> addTodo(@Valid @RequestBody TodoDto todoDto) {
      this.todoRepo.save(new Todo(null, todoDto.getTask(), false));

      return ResponseEntity.ok().build();
   }

   @GetMapping("/todos/{id}")
   public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
      return this.todoRepo.findById(id)
              .map(todo -> ResponseEntity.ok().body(todo))
              .orElse(ResponseEntity.notFound().build());
   }

   @PutMapping("/todos/{id}")
   public ResponseEntity<Object> updateTodoCompleted(@PathVariable Long id, @Valid @RequestBody TodoDto todoDto) {
      return this.todoRepo.findById(id)
              .map(todo -> {
                 todo.setTask(todoDto.getTask());
                 todo.setCompleted(todoDto.completed);
                 this.todoRepo.save(todo);
                 return ResponseEntity.ok().build();
              })
              .orElse(ResponseEntity.notFound().build());
   }

   @PutMapping("/todos/{id}/mark_completed")
   public ResponseEntity<Object> markCompleted(@PathVariable Long id) {
      return this.todoRepo.findById(id)
              .map(todo -> {
                 todo.setCompleted(true);
                 this.todoRepo.save(todo);
                 return ResponseEntity.ok().build();
              })
              .orElse(ResponseEntity.notFound().build());
   }

   @PutMapping("/todos/{id}/mark_incompleted")
   public ResponseEntity<Object> markIncomplete(@PathVariable Long id) {
      return this.todoRepo.findById(id)
              .map(todo -> {
                 todo.setCompleted(false);
                 this.todoRepo.save(todo);
                 return ResponseEntity.ok().build();
              })
              .orElse(ResponseEntity.notFound().build());
   }

   @DeleteMapping("/todos/{id}")
   public ResponseEntity<Object> deleteTodo(@PathVariable Long id) {
      return this.todoRepo.findById(id)
              .map(todo -> {
                 this.todoRepo.delete(todo);
                 return ResponseEntity.ok().build();
              })
              .orElse(ResponseEntity.notFound().build());
   }

}
