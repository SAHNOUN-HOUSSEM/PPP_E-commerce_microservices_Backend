package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void createProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public Product getProduct(int id) {
        System.out.println(id);
//        return productRepository.findById(id).orElse(null);
        return productRepository.findById(1).orElse(null);
    }
}