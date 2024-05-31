package com.ppp_microservice_ecommerce.authService.service;

import com.ppp_microservice_ecommerce.authService.dto.LoginUserDto;
import com.ppp_microservice_ecommerce.authService.dto.RegisterUserDto;
import com.ppp_microservice_ecommerce.authService.entity.AppUser;
import com.ppp_microservice_ecommerce.authService.entity.AppUserRoles;
import com.ppp_microservice_ecommerce.authService.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public record AuthService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
) {

    public AppUser register(RegisterUserDto registerUserDto) {
        Optional<AppUser> user = userRepository.findByUsername(registerUserDto.username());
        if (user.isPresent()) {
            throw new IllegalStateException("Username already taken");
        }
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

    public Boolean validateToken(final String token) {
        try {
            jwtService.validateToken(token);
        }catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
