package com.example.demo.dto;

import com.example.demo.entity.Todo;
import lombok.Data;

import java.util.List;
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<Todo> todos;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username,List<Todo> todos,  List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.todos = todos;
    }
}
