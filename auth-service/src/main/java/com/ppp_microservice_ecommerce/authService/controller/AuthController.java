package com.ppp_microservice_ecommerce.authService.controller;

import com.ppp_microservice_ecommerce.authService.dto.LoginUserDto;
import com.ppp_microservice_ecommerce.authService.dto.RegisterUserDto;
import com.ppp_microservice_ecommerce.authService.entity.AppUser;
import com.ppp_microservice_ecommerce.authService.response.LoginResponse;
import com.ppp_microservice_ecommerce.authService.response.MeResponse;
import com.ppp_microservice_ecommerce.authService.service.AuthService;
import com.ppp_microservice_ecommerce.clients.auth.GetUserFromTokenDto;
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



    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterUserDto registerUserDto) {
        log.info("Registering a new user {}", registerUserDto);
        try {
            return authService.register(registerUserDto);
        } catch (Exception e) {
            log.error("Error registering user {}", registerUserDto, e);
            throw e;
        }
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginUserDto loginUserDto) {
        log.info("Logging in user {}", loginUserDto);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.username(), loginUserDto.password()));
        if (!authentication.isAuthenticated()) {
            throw new RuntimeException("Authentication failed");
        }
        log.info("User {} is authenticated", loginUserDto.username());
        return authService.login(loginUserDto);
    }

    @PostMapping("/validate")
    public Boolean validateToken(@RequestBody ValidateTokenDto validateTokenDto) {
        log.info("Validating token {}", validateTokenDto.token());
        return authService.validateToken(validateTokenDto.token());
    }

    @PostMapping("/getUserId")
    public Integer getUserIdFromToken(@RequestBody GetUserFromTokenDto getUserFromTokenDto) {
        System.out.println("getting user id from token");
        System.out.println("getUserFromTokenDto = " + getUserFromTokenDto);
        String token = getUserFromTokenDto.token();
        log.info("Getting user id from token {}", token);
        return authService.getUserIdFromToken(token);
    }

    @PostMapping("/me")
    public MeResponse getUserFromToken(@RequestBody GetUserFromTokenDto getUserFromTokenDto) {
        System.out.println("getting user from token");
        System.out.println("getUserFromTokenDto = " + getUserFromTokenDto);
        String token = getUserFromTokenDto.token();
        log.info("Getting user from token {}", token);
        return authService.getUserFromToken(token);
    }

}