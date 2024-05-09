package com.ppp_microservice_ecommerce.orders.service;

import com.ppp_microservice_ecommerce.orders.dto.OrderItemDto;
import com.ppp_microservice_ecommerce.orders.dto.OrderRequest;
import com.ppp_microservice_ecommerce.orders.entities.Order;
import com.ppp_microservice_ecommerce.orders.entities.OrderItem;
import com.ppp_microservice_ecommerce.orders.respository.OrderRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRespository orderRespository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber((UUID.randomUUID()).toString());

        List<OrderItem> orderItemsList = orderRequest.getOrderItemsList().stream()
                .map(orderItemDto -> mapFromDto(orderItemDto, order))
                .collect(Collectors.toList());

        order.setOrderItemsList(orderItemsList);
        orderRespository.save(order);
    }

    private OrderItem mapFromDto(OrderItemDto orderItemDto, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setCode(orderItemDto.getCode());
        orderItem.setOrder(order);
        return orderItem;
    }
}
