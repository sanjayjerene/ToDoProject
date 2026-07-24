package com.TodoProj.TodoProj.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    private String title;


    private Boolean isCompleted;
    // getters and setters
}

