package com.todos.todos.controllers.TodoDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoDto {

    @NotBlank
    public String task;

    public boolean completed;
}
