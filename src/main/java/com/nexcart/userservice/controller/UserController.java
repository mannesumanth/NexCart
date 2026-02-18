package com.nexcart.userservice.controller;


import com.nexcart.userservice.dto.LoginRequest;
import com.nexcart.userservice.dto.RegisterRequest;
import com.nexcart.userservice.dto.UserResponse;
import com.nexcart.userservice.entity.User;
import com.nexcart.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserResponse register(@Valid  @RequestBody RegisterRequest request){
        return userService.registerUser(request);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody LoginRequest request){
        return userService.loginUser(request);
    }
}
