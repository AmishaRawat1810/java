package com.demo.webapp.controller;

import com.demo.webapp.dto.CreateUserRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {
    @GetMapping("/")
    public Map<String, String> hello() {
        return Map.of(
                "message", "Protected API - you are authenticated",
                "status", "success"
        );
    }

    @PostMapping("/users")
    public Map<String, String> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        String details =
                "username: %s : email: %s".formatted(createUserRequest.getName(), createUserRequest.getEmail());
        return Map.of("message" , "User created successfully",
                "user", details);
    }
}
