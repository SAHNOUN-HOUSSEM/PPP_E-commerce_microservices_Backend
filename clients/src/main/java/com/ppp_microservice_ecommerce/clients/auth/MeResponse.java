package com.ppp_microservice_ecommerce.clients.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
@Getter
public class MeResponse {
   public UserDto user;
   public String token;

   public MeResponse(UserDto userDto, String newToken) {
        this.user = userDto;
        this.token = newToken;
   }
}
