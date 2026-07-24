package com.TodoProj.TodoProj.service;


import com.TodoProj.TodoProj.Repository.TodoRepository;
import com.TodoProj.TodoProj.models.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo CreateTodo(Todo todo){return todoRepository.save(todo);}

    public Todo getTodoById(Long id){return todoRepository.findById(id).orElseThrow(()->new RuntimeException("Todo not found"));}

    public Page<Todo> getAllTodoPages(int page,int size){
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }

    public List<Todo> getAllTodo(){return todoRepository.findAll();}

    public Todo update(Todo todo){return todoRepository.save(todo);}

    public void deleteTodo(Todo todo){todoRepository.delete(todo);}

}
