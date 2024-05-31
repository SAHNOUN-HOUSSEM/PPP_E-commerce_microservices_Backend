package com.ppp_microservice_ecommerce.clients.auth;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(
        "AUTH-SERVICE"
)
public interface AuthClient {
    @PostMapping("auth/validate")
    Boolean validateToken(@RequestBody ValidateTokenDto validateTokenDto);
}


