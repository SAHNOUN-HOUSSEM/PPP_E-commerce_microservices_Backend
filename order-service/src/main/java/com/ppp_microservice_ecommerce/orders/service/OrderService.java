package com.ppp_microservice_ecommerce.orders.service;

import com.ppp_microservice_ecommerce.amqp.RabbitMQMessageProducer;
import com.ppp_microservice_ecommerce.clients.notifications.OrderNotificationRequest;
import com.ppp_microservice_ecommerce.clients.orders.OrderItemDto;
import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.clients.products.ProductClient;
import com.ppp_microservice_ecommerce.clients.products.ProductResponse;
import com.ppp_microservice_ecommerce.clients.notifications.OrderNotificationConfig;
import com.ppp_microservice_ecommerce.orders.entities.Order;
import com.ppp_microservice_ecommerce.orders.entities.OrderItem;
import com.ppp_microservice_ecommerce.orders.respository.OrderRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRespository orderRespository;
    private final ProductClient ProductClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final OrderNotificationConfig orderNotificationConfig;

    public ResponseEntity<String> placeOrder(OrderRequest orderRequest){
        Order order = new Order();

        List<OrderItem> orderItemsList = orderRequest.getOrderItemsList().stream()
                .map(orderItemDto -> mapFromDto(orderItemDto, order))
                .collect(Collectors.toList());

        order.setOrderItemsList(orderItemsList);
        order.setUserId(order.getUserId());


        // call product service to check if all products are in stock
        System.out.println("Checking if all products are in stock");
        List<ProductResponse> products = ProductClient.getProductsByIds(orderRequest);
        boolean allProductsInStock = products.stream().allMatch(ProductResponse::isInStock);
        System.out.println("Checking if all products are in stock: " + allProductsInStock);
        if (!allProductsInStock){
            throw new IllegalArgumentException("Some products are not in stock or not enough.");
        }

        orderRespository.save(order);
        System.out.println("Order"+ order.getId());

        // send message to notification via rabbitmq
        OrderNotificationRequest orderNotificationRequest = new OrderNotificationRequest();
        orderNotificationRequest.setOrderID(order.getId());
        orderNotificationRequest.setMessage("Order placed successfully");


        System.out.println("Updating product stock");
        ProductClient.updateStock(orderRequest);

        System.out.println("Sending notification to rabbitmq");

        rabbitMQMessageProducer.publish(orderNotificationRequest, orderNotificationConfig.getInternalExchange(), orderNotificationConfig.getInternalNotificationsRoutingKey());
        System.out.println("Sent notification");

        // emit event to sse


        // return ok response to user
        return ResponseEntity.ok("Order placed successfully");
    }

    private OrderItem mapFromDto(OrderItemDto orderItemDto, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setProductID(orderItemDto.getId());
        orderItem.setOrder(order);
        return orderItem;
    }
}
