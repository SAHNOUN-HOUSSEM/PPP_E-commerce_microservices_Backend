package com.ppp_microservice_ecommerce.orders.controller;

import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public String mot()
    {
        return "Hello";
    }

    @GetMapping("/test")
    public String test(){
        System.out.println("order service is working");
        return "Order service is working";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";
    }
}
