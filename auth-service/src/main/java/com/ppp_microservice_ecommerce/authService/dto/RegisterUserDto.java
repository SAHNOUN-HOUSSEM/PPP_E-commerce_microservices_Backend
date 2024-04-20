package com.ppp_microservice_ecommerce.authService.dto;

public record RegisterUserDto (
         String username,
         String password,
         String firstName,
         String lastName,
         String email
){}
