package com.demo.webapp.controller;

import com.demo.webapp.service.JwtProperties;
import com.demo.webapp.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody String username) {
        JwtService jwtService = new JwtService(new JwtProperties());
        String token = jwtService.generateToken(username);
        return Map.of("token", token);
    }
}
