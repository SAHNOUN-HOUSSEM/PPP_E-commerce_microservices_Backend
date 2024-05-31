package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.products.dto.ProductResponse;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


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

    public Product getProduct(Integer id) {
        System.out.println(id);
        return productRepository.findById(id).orElse(null);
    }



    public List<ProductResponse> areInStock(OrderRequest order) {
        return order.getOrderItemsList().stream()
                .map(orderItem -> {
                    Product product = productRepository.findById(orderItem.getId()).orElse(null);
                    if (product != null) {
                        return ProductResponse.builder()
                                .id(product.getId())
                                .isInStock(orderItem.getQuantity() <= product.getQuantity())
                                .build();
                    } else {
                        return ProductResponse.builder()
                                .id(orderItem.getId())
                                .isInStock(false)
                                .build();
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void updateStock(OrderRequest order) {
        order.getOrderItemsList().forEach(orderItemDto -> {
            Product product = productRepository.findById(orderItemDto.getId()).orElse(null);
            if (product != null) {
                product.setQuantity(product.getQuantity() - orderItemDto.getQuantity());
                productRepository.save(product);
            }
        });
    }
}
