package com.ecommerce.userservice.dto;

public record UserDto(
        String name,
        String phone,
        String email,
        String password,
        String role
){}
