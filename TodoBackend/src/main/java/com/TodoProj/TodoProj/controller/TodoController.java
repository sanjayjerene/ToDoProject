package com.TodoProj.TodoProj.controller;
import java.util.List;
import com.TodoProj.TodoProj.models.Todo;
import com.TodoProj.TodoProj.service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo/v1")
@Slf4j
public class TodoController {

    @Autowired
    private TodoService todoService;


    @GetMapping("/get")
    public ResponseEntity<List<Todo>> getAllTodos() {
        return new ResponseEntity<List<Todo>>(todoService.getAllTodo(),HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Todo Found successfully"),
            @ApiResponse(responseCode = "404",description = "Todo not Found")
    })
    @GetMapping("/{id}")
    public ResponseEntity <Todo> getTodoById(@PathVariable Long id){
        try{
            Todo createdTodo= todoService.getTodoById(id);
            return  new ResponseEntity<>(createdTodo,HttpStatus.OK);
        }
        catch (RuntimeException exception){
            log.info("error");
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/page")
    public ResponseEntity<Page<Todo>> getTodosPage(@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(todoService.getAllTodoPages(page,size),HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Todo> CreateUser(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.CreateTodo(todo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
        todo.setId(id);
        return new ResponseEntity<>(todoService.update(todo), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteTodoId(@PathVariable long id) {
        try {
            todoService.deleteTodo(todoService.getTodoById(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
