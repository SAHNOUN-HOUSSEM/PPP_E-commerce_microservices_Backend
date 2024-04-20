package com.ppp_microservice_ecommerce.authService.repository;

import com.ppp_microservice_ecommerce.authService.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsername(String username);
}
