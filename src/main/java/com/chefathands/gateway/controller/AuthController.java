package com.chefathands.gateway.controller;

import com.chefathands.gateway.dto.LoginRequest;
import com.chefathands.gateway.dto.LoginResponse;
import com.chefathands.gateway.dto.RegisterRequest;
import com.chefathands.gateway.dto.UserDTO;
import com.chefathands.gateway.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    
    private final AuthService authService;
    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterRequest request) {
        UserDTO user = authService.register(request);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {  
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}