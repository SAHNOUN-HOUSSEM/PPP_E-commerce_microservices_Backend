package com.ppp_microservice_ecommerce.clients.orders;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Integer id;
    private int quantity;
}
