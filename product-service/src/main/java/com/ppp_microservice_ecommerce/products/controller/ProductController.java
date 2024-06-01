package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.products.dto.ProductResponse;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping()
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        log.info("Getting product");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        log.info(pageable.toString());
        return productService.getProducts(pageable);
    }

    @PostMapping("/ids")
    public List<ProductResponse> getProductsByIds(@RequestBody OrderRequest order) {
        log.info("Getting products by ids");
        return productService.areInStock(order);
    }

    @PostMapping("/update")
    public void updateStock(@RequestBody OrderRequest order) {
        log.info("Updating product stock");
        productService.updateStock(order);
    }

    @PostMapping()
    public void createProduct(@RequestBody Product product)
    {
        System.out.println(product.getId());
        log.info("Creating product");
        productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        log.info("Getting product by id");
        return productService.getProduct(id);
    }

    @PostMapping("/lists")
    public List<Product> getProductsByIds(@RequestBody List<Integer> ids) {
        log.info("Getting product by ids");
        return productService.getProductsByIds(ids);
    }
}
