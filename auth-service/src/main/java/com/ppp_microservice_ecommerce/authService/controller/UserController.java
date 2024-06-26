package com.ppp_microservice_ecommerce.authService.controller;

import com.ppp_microservice_ecommerce.authService.entity.AppUser;
import com.ppp_microservice_ecommerce.authService.service.JwtService;
import com.ppp_microservice_ecommerce.authService.service.UserService;
import com.ppp_microservice_ecommerce.clients.auth.UpdateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("")
    public List<AppUser> getAllUsers() {
        log.info("Getting all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable("id") Integer id, @RequestHeader("Authorization") String BearerToken) {
        log.info("Getting user by id {}", id);
        System.out.println("BearerToken = " + BearerToken);
        String token = BearerToken.substring(7);
        Integer userIdFromToken = jwtService.getUserIdFromToken(token);
        log.info("logged in user id {}", userIdFromToken);
        if (!userIdFromToken.equals(id)) {
            throw new RuntimeException("Unauthorized");
        }
        return userService.getUserById(userIdFromToken);
    }

    @PutMapping()
    public AppUser updateUser(@RequestBody UpdateUserDto updateUserDto, @RequestHeader("Authorization") String BearerToken) {
        System.out.println("BearerToken = " + BearerToken);
        System.out.println("updateUserDto = " + updateUserDto);
        String token = BearerToken.substring(7);
        Integer userIdFromToken = jwtService.getUserIdFromToken(token);
        log.info("Updating user by id {}", userIdFromToken);
        return userService.updateUser(userIdFromToken, updateUserDto);
    }

}
