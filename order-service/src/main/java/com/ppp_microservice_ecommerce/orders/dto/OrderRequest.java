package com.ppp_microservice_ecommerce.orders.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private List<OrderItemDto> orderItemsList;
}
