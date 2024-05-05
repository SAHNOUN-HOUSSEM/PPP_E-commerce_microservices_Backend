package com.ppp_microservice_ecommerce.orders.respository;

import com.ppp_microservice_ecommerce.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order,Long> {
}
