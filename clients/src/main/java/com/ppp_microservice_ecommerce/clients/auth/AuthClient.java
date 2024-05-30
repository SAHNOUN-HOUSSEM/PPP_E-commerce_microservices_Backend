package com.ppp_microservice_ecommerce.clients.auth;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "AUTH-SERVICE",
        path = "/auth"
)
public interface AuthClient {
    @PostMapping("/validate")
    String validateToken(@RequestBody ValidateTokenDto validateTokenDto) ;
}
