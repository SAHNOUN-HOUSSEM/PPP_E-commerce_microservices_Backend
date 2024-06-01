package com.ppp_microservice_ecommerce.orders.respository;

import com.ppp_microservice_ecommerce.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRespository extends JpaRepository<Order,Long> {
    //find all orders by user id
    List<Order> findAllByUserId(Integer userId);
}
