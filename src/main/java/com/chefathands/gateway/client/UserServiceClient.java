package com.chefathands.gateway.client;

import com.chefathands.gateway.dto.LoginRequest;
import com.chefathands.gateway.dto.LoginResponse;
import com.chefathands.gateway.dto.RegisterRequest;
import com.chefathands.gateway.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {
    
    private final RestTemplate restTemplate;
    
    @Value("${services.user.url}")
    private String userServiceUrl;
    
    public UserServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public UserDTO register(RegisterRequest request) {
        String url = userServiceUrl + "/api/users/register";  
        return restTemplate.postForObject(url, request, UserDTO.class);
    }
    
    public LoginResponse login(LoginRequest request) {  
        String url = userServiceUrl + "/api/users/login";  
        return restTemplate.postForObject(url, request, LoginResponse.class);
    }
}