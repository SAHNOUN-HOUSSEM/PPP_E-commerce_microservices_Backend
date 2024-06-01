package com.ppp_microservice_ecommerce.authService.service;

import com.ppp_microservice_ecommerce.authService.entity.AppUser;
import com.ppp_microservice_ecommerce.authService.repository.UserRepository;
import com.ppp_microservice_ecommerce.clients.auth.UpdateUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    //update user
    public AppUser updateUser(Integer id, UpdateUserDto updateUserDto) {
        AppUser user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        if (updateUserDto.username() != null) {
            user.setUsername(updateUserDto.username());
        }
        if (updateUserDto.firstName() != null) {
            user.setFirstName(updateUserDto.firstName());
        }
        if (updateUserDto.lastName() != null) {
            user.setLastName(updateUserDto.lastName());
        }
        if (updateUserDto.email() != null) {
            user.setEmail(updateUserDto.email());
        }
        System.out.println("updateUserDto = " + updateUserDto);
        System.out.println("user = " + user);
        return userRepository.save(user);
    }

}
