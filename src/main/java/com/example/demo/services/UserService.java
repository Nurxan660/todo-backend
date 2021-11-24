package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.entity.Todo;
import com.example.demo.repository.TodoRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

@Autowired
    UserRepository userRepository;
    @Autowired
    TodoRepository todoRepository;


    public boolean saveUser (User user) {

        userRepository.save(user);
        return true;

    }
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public boolean addUserTodos(Long userId, List<Integer> todosId) {
        Optional<User> userOpt = userRepository.findById(userId);
        User user = userOpt.orElse(null);
        if (user == null) {
            return false;

        }

        List<Todo> todos = new ArrayList<>();
        todosId.forEach(id -> todoRepository.findById(id.longValue()).ifPresent(p -> todos.add(p)));
        user.setTodo(todos);
        userRepository.save(user);
        return true;
    }



public boolean deleteUser(Long id){
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
}







}
