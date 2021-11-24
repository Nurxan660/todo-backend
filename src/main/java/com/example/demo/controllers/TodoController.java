package com.example.demo.controllers;

import com.example.demo.entity.Todo;
import com.example.demo.entity.User;
import com.example.demo.repository.TodoRepository;
import com.example.demo.services.TodoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/todo")
public class TodoController {
    @Autowired
    TodoService todoService;
    @Autowired
    TodoRepository todoRepository;

   @GetMapping("/users/{userId}/todos")
   @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getTodos(@PathVariable Long userId)  {
        List<Todo> todos=todoService.getTodo(userId);
        if(todos==null){
            return ResponseEntity.ok("User not found");
        }
        return ResponseEntity.ok(todos);

    }

    @PostMapping("/users/{userId}/todos")
    @PreAuthorize("hasRole('USER')")
     public ResponseEntity create(@PathVariable Long userId,@RequestBody Todo todo) {

        boolean result = todoService.createTodo(userId,todo);
        if (result) {
            List<Todo> todos=todoService.getTodo(userId);
            return ResponseEntity.ok(todos);

        }
        return new ResponseEntity("todo already exist", HttpStatus.BAD_REQUEST);


    }

    @PutMapping("/users/{userId}/todos/{todosId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity update(@PathVariable Long userId,@PathVariable Long todosId,@RequestBody Todo todo) {

if(todoService.updateTodo(userId,todosId,todo.getDescription(),todo.isCompleted())){
    List<Todo> allTodos= todoRepository.findAllByUserId(userId);
    return ResponseEntity.ok(allTodos);

}
        return ResponseEntity.ok("todo или user не существует");

    }


    @DeleteMapping("/users/{userId}/todos/{todosId}")
    @PreAuthorize("hasRole('USER')")

    public ResponseEntity update(@PathVariable Long userId,@PathVariable Long todosId) {
        if(todoService.deleteTodo(userId,todosId)){
            return ResponseEntity.ok("Туду было удалено");

        }
        return ResponseEntity.ok("todo или user не существует");


    }


}
