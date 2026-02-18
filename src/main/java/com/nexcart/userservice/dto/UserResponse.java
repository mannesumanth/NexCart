package com.nexcart.userservice.dto;

import com.nexcart.userservice.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse  {
    private Long id;
    private String name;
    private String email;
    private String  role;
}
