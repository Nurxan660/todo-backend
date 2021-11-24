package com.example.demo.services;

import com.example.demo.entity.Todo;
import com.example.demo.entity.User;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    UserRepository userRepository;

    public List<Todo> getTodo(Long id){
        if(userRepository.existsById(id)) {
            return  todoRepository.findByUserId(id);
        }
        return null;
    }

    public boolean createTodo (Long id,Todo todo) {
        Optional<User> opt=userRepository.findById(id);
        if(opt.isPresent()) {
            todo.setUser(opt.get());

            todo.setDate(new Date());
            todoRepository.save(todo);
            return true;
        }
        return false;

    }

    public boolean updateTodo(Long userId,Long todoId,String desc,boolean completed){
        Optional<Todo> t=todoRepository.findById(todoId);
        Optional<User> s=userRepository.findById(userId);
        User user=s.orElse(null);
        Todo todos=t.orElse(null);
        if(todos==null||user==null){
            return false;
        }
        todos.setCompleted(completed);
        todos.setDescription(desc);
        todoRepository.save(todos);
        return true;
    }

    public boolean deleteTodo(Long userId,Long todosId){
        Optional<Todo> t=todoRepository.findById(todosId);
        Optional<User> s=userRepository.findById(userId);
        User user=s.orElse(null);
        Todo todos=t.orElse(null);
        if(todos==null||user==null){
            return false;
        }
        todoRepository.deleteById(todosId);
        return true;
    }










}
