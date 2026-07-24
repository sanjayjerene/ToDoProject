package com.TodoProj.TodoProj.Repository;


import com.TodoProj.TodoProj.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TodoRepository extends JpaRepository <Todo,Long> {

}
