package com.ppp_microservice_ecommerce.clients.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private UserRoles role;
}
