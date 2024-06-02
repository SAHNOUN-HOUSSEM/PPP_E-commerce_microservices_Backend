package com.ppp_microservice_ecommerce.authService.response;

import com.ppp_microservice_ecommerce.authService.entity.AppUser;

public record MeResponse (
        AppUser user,
        String token
){
}
