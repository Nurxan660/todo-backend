package com.example.demo.controllers;

import com.example.demo.entity.EnumRole;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;




    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity update(@PathVariable Long id,@RequestBody User user) {
        Optional<User> userById= userService.getUser(id);
        


            if(userById.isPresent()) {
                User f=userById.get();
                System.out.println(f);
                f.setId(f.getId());
                f.setNickname(user.getNickname());
                f.setFirstName(user.getFirstName());
                f.setPassword(user.getPassword());
                userRepository.save(f);

                return ResponseEntity.ok("Updated");
            }


        return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity delete(@PathVariable Long id) {
             if(userService.deleteUser(id)) {
                 return ResponseEntity.ok("User успешно удален");
             }
        return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
            }

      @GetMapping("/get")
      @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity check() {
       List<User> allUsers=  userRepository.findAllByRolesName(EnumRole.ROLE_USER);
       if(allUsers==null){
           return new ResponseEntity("Users not found", HttpStatus.NOT_FOUND);
       }

          return ResponseEntity.ok(allUsers);
    }









}
