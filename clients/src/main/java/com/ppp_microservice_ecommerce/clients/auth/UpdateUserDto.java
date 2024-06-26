package com.ppp_microservice_ecommerce.clients.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record UpdateUserDto(
        @NotNull(message = "Username is required")
         String username,
        @NotNull(message = "First name is required")
         String firstName,
        @NotNull(message = "Last name is required")
         String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "Email is invalid")
         String email
){}
