package com.ppp_microservice_ecommerce.clients.products;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        value = "product",
        path = "/products"
)
public interface ProductClient {
}
