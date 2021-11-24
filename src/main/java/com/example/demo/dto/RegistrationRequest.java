package com.example.demo.dto;

import lombok.Data;

import java.util.Set;
@Data
public class RegistrationRequest {

    private String firstName;
    private String nickname;
    private String password;
    private Set<String> role;
}
