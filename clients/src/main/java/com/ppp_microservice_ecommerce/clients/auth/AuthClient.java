package com.ppp_microservice_ecommerce.clients.auth;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        name = "auth-service",
        url = "http://localhost:8080"
)
public interface AuthClient {
    @PostMapping("auth/validate")
    Boolean validateToken(@RequestBody ValidateTokenDto validateTokenDto);
}


