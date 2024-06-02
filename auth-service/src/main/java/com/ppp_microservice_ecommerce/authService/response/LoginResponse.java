package com.ppp_microservice_ecommerce.authService.response;

public record LoginResponse (
        String token,
        String username,
        String role
){
}
