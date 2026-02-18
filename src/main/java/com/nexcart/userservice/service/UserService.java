package com.nexcart.userservice.service;


import com.nexcart.userservice.dto.LoginRequest;
import com.nexcart.userservice.dto.RegisterRequest;
import com.nexcart.userservice.dto.UserResponse;
import com.nexcart.userservice.entity.User;
import com.nexcart.userservice.repository.UserRepository;
import com.nexcart.userservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    //Registering User
    public UserResponse registerUser(RegisterRequest request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .build();
    }

    // Login User
    public Map<String, Object> loginUser(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail()).
                orElseThrow(()-> new RuntimeException("USer Not Found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        String token = jwtService.generateToken(user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token",token);
        response.put("email",user.getEmail());
        response.put("role",user.getRole());
//        return UserResponse.builder()
//                .id(user.getId())
//                .name(user.getName())
//                .email(user.getEmail())
//                .role(user.getRole())
//                .build();
        return response;
    }

}
