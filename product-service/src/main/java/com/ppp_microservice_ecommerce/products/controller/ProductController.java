package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.products.entites.Product;
import com.ppp_microservice_ecommerce.products.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Slf4j

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get")
    public List<Product> getProducts() {
        log.info("Getting product");
        return productService.getProducts();
    }

    @PostMapping("/create")
    public void createProduct(@RequestBody Product product)
    {
        System.out.println(product.getId());
        log.info("Creating product");
        productService.createProduct(product);
    }
}
