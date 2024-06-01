package com.ppp_microservice_ecommerce.orders.controller;

import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.orders.entities.OrderResponse;
import com.ppp_microservice_ecommerce.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping()
    public List<OrderResponse> getOrders(){
        return orderService.getOrders();
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
