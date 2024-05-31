package com.ppp_microservice_ecommerce.clients.products;

import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "product-service",
        path = "/product"
)
public interface ProductClient {
    @PostMapping("/ids")
    public List<ProductResponse> getProductsByIds(@RequestBody OrderRequest order);

    @PostMapping("/update")
    public void updateStock(@RequestBody OrderRequest order);
}
