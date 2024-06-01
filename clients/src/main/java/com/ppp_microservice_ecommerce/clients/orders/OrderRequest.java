package com.ppp_microservice_ecommerce.clients.orders;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private List<OrderItemDto> orderItemsList;
    private Integer userId;
}
