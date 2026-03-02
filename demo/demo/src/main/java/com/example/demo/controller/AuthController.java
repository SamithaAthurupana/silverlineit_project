package com.example.demo.controller;

import com.example.demo.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        // Simple hardcoded user
        if ("admin".equals(username) && "1234".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return Map.of("token", token);
        }

        throw new RuntimeException("Invalid credentials");
    }
}