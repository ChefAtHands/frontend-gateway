package com.chefathands.gateway.service;

import com.chefathands.gateway.client.LoggingServiceClient;
import com.chefathands.gateway.client.UserServiceClient;
import com.chefathands.gateway.dto.LoginRequest;
import com.chefathands.gateway.dto.LoginResponse;
import com.chefathands.gateway.dto.RegisterRequest;
import com.chefathands.gateway.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    private final UserServiceClient userServiceClient;
    private final LoggingServiceClient loggingServiceClient;
    
    public AuthService(UserServiceClient userServiceClient, LoggingServiceClient loggingServiceClient) {
        this.userServiceClient = userServiceClient;
        this.loggingServiceClient = loggingServiceClient;
    }
    
    public UserDTO register(RegisterRequest request) {
        UserDTO user = userServiceClient.register(request);
        loggingServiceClient.log("USER_REGISTERED", user.getId(), "New user: " + user.getUsername());
        return user;
    }
    
    public LoginResponse login(LoginRequest request) {  
        LoginResponse response = userServiceClient.login(request);
        loggingServiceClient.log("USER_LOGIN", response.getUserId(), "User logged in: " + response.getUsername());
        return response;
    }
}