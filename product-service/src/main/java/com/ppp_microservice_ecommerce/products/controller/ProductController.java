package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.products.dto.ProductResponse;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/product")
@Slf4j

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> getProducts() {
        log.info("Getting product");
        return productService.getProducts();
    }

    @PostMapping("/names")
    public Map<Integer,String> getProductsByIds(@RequestBody Set<Integer> productIDs) {
        log.info("Getting products by ids");
        return productService.getProductNames(productIDs);
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
