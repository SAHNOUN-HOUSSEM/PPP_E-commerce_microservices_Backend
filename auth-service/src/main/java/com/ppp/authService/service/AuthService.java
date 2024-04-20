package com.ppp.authService.service;

import com.ppp.authService.dto.LoginUserDto;
import com.ppp.authService.dto.RegisterUserDto;
import com.ppp.authService.entity.AppUser;
import com.ppp.authService.entity.AppUserRoles;
import com.ppp.authService.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record AuthService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
) {

    public AppUser register(RegisterUserDto registerUserDto) {
        AppUser appUser =AppUser.builder()
                .firstName(registerUserDto.firstName())
                .lastName(registerUserDto.lastName())
                .email(registerUserDto.email())
                .username(registerUserDto.username())
                .role(AppUserRoles.USER)
                .build();
        appUser.setPassword(passwordEncoder.encode(registerUserDto.password()));
        userRepository.save(appUser);

        return appUser;
    }

    public String login(LoginUserDto loginUserDto) {
        return jwtService.generateToken(loginUserDto.username());
    }

    public void validateToken(final String token) {
        jwtService.validateToken(token);
    }

}
