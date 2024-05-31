package com.ppp_microservice_ecommerce.authService.controller;

import com.ppp_microservice_ecommerce.authService.dto.UpdateUserDto;
import com.ppp_microservice_ecommerce.authService.entity.AppUser;
import com.ppp_microservice_ecommerce.authService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<AppUser> getAllUsers() {
        log.info("Getting all users");
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public AppUser getUserById(@PathVariable Integer id) {
        log.info("Getting user by id {}", id);
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public AppUser updateUser(@PathVariable Integer id, @RequestBody UpdateUserDto updateUserDto) {
        log.info("Updating user by id {}", id);
        return userService.updateUser(id, updateUserDto);
    }

}
