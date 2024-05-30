package com.ppp_microservice_ecommerce.authService.controller;

import com.ppp_microservice_ecommerce.authService.dto.LoginUserDto;
import com.ppp_microservice_ecommerce.authService.dto.RegisterUserDto;
import com.ppp_microservice_ecommerce.authService.entity.AppUser;
import com.ppp_microservice_ecommerce.authService.service.AuthService;
import com.ppp_microservice_ecommerce.clients.auth.ValidateTokenDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public record AuthController(AuthService authService, AuthenticationManager authenticationManager) {


    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterUserDto registerUserDto) {
        log.info("Registering a new user {}", registerUserDto);
        return authService.register(registerUserDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserDto loginUserDto) {
        log.info("Logging in user {}", loginUserDto);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.username(), loginUserDto.password()));
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Authentication failed");
        }
        log.info("User {} is authenticated", loginUserDto.username());
        return authService.login(loginUserDto);
    }

    @PostMapping("/validate")
    public String validateToken(@RequestBody ValidateTokenDto validateTokenDto) {
        log.info("Validating token {}", validateTokenDto.token());
        authService.validateToken(validateTokenDto.token());
        return "Token is valid";
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}