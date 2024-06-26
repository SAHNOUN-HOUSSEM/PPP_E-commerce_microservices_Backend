package com.ppp_microservice_ecommerce.orders.controller;

import com.ppp_microservice_ecommerce.clients.auth.*;
import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.orders.entities.OrderResponse;
import com.ppp_microservice_ecommerce.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final AuthClient authClient;


    @GetMapping()
    public List<OrderResponse> getOrders( @RequestHeader("Authorization") String BearerToken) {
        System.out.println("BearerToken = " + BearerToken);
        String token = BearerToken.substring(7);
        ValidateTokenDto validateTokenDto = new ValidateTokenDto(token);
        Boolean isValid = authClient.validateToken(validateTokenDto);
        if (!isValid) {
            throw new RuntimeException("Invalid token");
        }
        System.out.println("Token is valid");
        System.out.println("token = " + token);
        GetUserFromTokenDto getUserFromTokenDto = new GetUserFromTokenDto(token);
        MeResponse data = authClient.getUserFromToken(getUserFromTokenDto);
        UserDto user = data.getUser();
        System.out.println("user = " + user);
        return orderService.getOrders(user);
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("order service is working");
        return "Order service is working";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest, @RequestHeader("Authorization") String BearerToken) {
        System.out.println("BearerToken = " + BearerToken);
        String token = BearerToken.substring(7);
        ValidateTokenDto validateTokenDto = new ValidateTokenDto(token);
        Boolean isValid = authClient.validateToken(validateTokenDto);
        if (!isValid) {
            throw new RuntimeException("Invalid token");
        }
        System.out.println("Token is valid");
        System.out.println("token = " + token);
        GetUserFromTokenDto getUserFromTokenDto = new GetUserFromTokenDto(token);
        Integer userId = authClient.getUserIdFromToken(getUserFromTokenDto);
        System.out.println("userId = " + userId);
        orderRequest.setUserId(userId);
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}
