package com.ppp.authService.controller;

import com.ppp.authService.dto.LoginUserDto;
import com.ppp.authService.dto.RegisterUserDto;
import com.ppp.authService.dto.ValidateTokenDto;
import com.ppp.authService.entity.AppUser;
import com.ppp.authService.service.AuthService;
import javassist.tools.web.BadHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
public record AuthController(
        AuthService authService,
        AuthenticationManager authenticationManager
) {


    @PostMapping("/register")
    public AppUser register(
            @RequestBody RegisterUserDto registerUserDto
    ){
        log.info("Registering a new user {}", registerUserDto);
        return authService.register(registerUserDto);
    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginUserDto loginUserDto
    ){
        log.info("Logging in user {}", loginUserDto);
      Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.username(),
                        loginUserDto.password()
                )
        );
      if (!authentication.isAuthenticated()){
            throw new RuntimeException("Authentication failed");
      }
        log.info("User {} is authenticated", loginUserDto.username());
        return authService.login(loginUserDto);
    }

    @PostMapping("/validate")
        public String validateToken(
            @RequestBody ValidateTokenDto validateTokenDto
            ){
        log.info("Validating token {}", validateTokenDto.token());
        authService.validateToken(validateTokenDto.token());
        return "Token is valid";
    }
}