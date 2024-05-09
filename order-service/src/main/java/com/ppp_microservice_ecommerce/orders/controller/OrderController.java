package com.ppp_microservice_ecommerce.orders.controller;

import com.ppp_microservice_ecommerce.orders.dto.OrderRequest;
import com.ppp_microservice_ecommerce.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    // TODO: Return the actual order and not a String reponse
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}
