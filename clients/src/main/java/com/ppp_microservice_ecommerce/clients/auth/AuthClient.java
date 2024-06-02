package com.ppp_microservice_ecommerce.clients.auth;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient("AUTH-SERVICE")
public interface AuthClient {
    @PostMapping("auth/validate")
    Boolean validateToken(@RequestBody ValidateTokenDto validateTokenDto);

    @PostMapping("auth/getUserId")
    Integer getUserIdFromToken(@RequestBody GetUserFromTokenDto getUserFromTokenDto);

    @PostMapping("auth/me")
    AppUser getUserFromToken(@RequestBody GetUserFromTokenDto getUserFromTokenDto);

    @GetMapping("user")
    List<AppUser> getAllUsers();

    @GetMapping("user/{id}")
    AppUser getUserById(@PathVariable("id") Integer id);

    @PutMapping("user")
    AppUser updateUser(@RequestBody UpdateUserDto updateUserDto);

}


