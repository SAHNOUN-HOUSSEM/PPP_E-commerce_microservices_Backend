package com.ppp_microservice_ecommerce.authService.service;

import com.ppp_microservice_ecommerce.amqp.RabbitMQMessageProducer;
import com.ppp_microservice_ecommerce.authService.dto.LoginUserDto;
import com.ppp_microservice_ecommerce.authService.dto.RegisterUserDto;
import com.ppp_microservice_ecommerce.authService.entity.AppUser;
import com.ppp_microservice_ecommerce.authService.entity.AppUserRoles;
import com.ppp_microservice_ecommerce.authService.repository.UserRepository;
import com.ppp_microservice_ecommerce.authService.response.LoginResponse;
import com.ppp_microservice_ecommerce.authService.response.MeResponse;
import com.ppp_microservice_ecommerce.clients.notifications.UserNotificationConfig;
import com.ppp_microservice_ecommerce.clients.notifications.UserNotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public record AuthService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        RabbitMQMessageProducer rabbitMQMessageProducer,
        UserNotificationConfig userNotificationConfig
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
                .role(registerUserDto.role())
                .build();
        appUser.setPassword(passwordEncoder.encode(registerUserDto.password()));
        userRepository.save(appUser);

        // send notification to notification service
        UserNotificationRequest userNotificationRequest = new UserNotificationRequest();

        userNotificationRequest.setUserID(appUser.getId());
        userNotificationRequest.setUsername(appUser.getUsername());
        userNotificationRequest.setEmail(appUser.getEmail());
        userNotificationRequest.setMessage("User registered successfully");

        log.info("Sending notification to notification service");
        rabbitMQMessageProducer.publish(userNotificationRequest, userNotificationConfig.getInternalExchange(), userNotificationConfig.getInternalNotificationsRoutingKey());
        log.info("Notification sent to notification service");

        return appUser;
    }

    public LoginResponse login(LoginUserDto loginUserDto) {
        Optional<AppUser> user = userRepository.findByUsername(loginUserDto.username());
        if (user.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        if (!passwordEncoder.matches(loginUserDto.password(), user.get().getPassword())) {
            throw new IllegalStateException("Invalid password");
        }
        //find the user and generate a token
        String token = jwtService.generateToken(user.get().getUsername(), user.get().getId());
        return new LoginResponse(token, user.get().getUsername(), user.get().getRole().name());
    }

    public Boolean validateToken(final String token) {
        try {
            jwtService.validateToken(token);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    public Integer getUserIdFromToken(String token) {
        return jwtService.getUserIdFromToken(token);
    }

    public MeResponse getUserFromToken(String token) {
        AppUser user= userRepository.findById(jwtService.getUserIdFromToken(token)).orElseThrow();
        String newToken = jwtService.generateToken(user.getUsername(), user.getId());
        return new MeResponse(user, newToken);
    }
}
