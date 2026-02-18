package com.nexcart.userservice.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email can't be blank")
    private String email;

    @NotBlank(message = "Password cant' be null")
    private String password;

}
