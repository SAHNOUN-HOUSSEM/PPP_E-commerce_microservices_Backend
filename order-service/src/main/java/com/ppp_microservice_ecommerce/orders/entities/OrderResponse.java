package com.ppp_microservice_ecommerce.orders.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private Integer orderID;
    private Map<String,Integer> products;
}
