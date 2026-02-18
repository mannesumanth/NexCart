package com.nexcart.userservice.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Size(min =6,message = "Password must be atleast 6 characters")
    private String password;

    @NotBlank(message = "Role can't be blank")
    private String role;
}
