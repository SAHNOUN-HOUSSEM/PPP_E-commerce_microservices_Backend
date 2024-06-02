package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.products.dto.ProductResponse;
import com.ppp_microservice_ecommerce.products.entities.Brand;
import com.ppp_microservice_ecommerce.products.entities.Category;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.repository.BrandRepository;
import com.ppp_microservice_ecommerce.products.repository.CategoryRepository;
import com.ppp_microservice_ecommerce.products.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    public Page<Product> getProducts(
            Pageable pageable
    ) {
        return productRepository.findAll(pageable);
    }

    public void createProduct(Product product) {
        productRepository.saveAndFlush(product);
    }

    public Product getProduct(Integer id) {
        System.out.println(id);
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByIds(List<Integer> ids) {
        return productRepository.findAllById(ids);
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

    public Map<Integer, String> getProductNames(Set<Integer> productIDs) {
        return productRepository.findAllById(productIDs).stream()
                .collect(Collectors.toMap(Product::getId, Product::getName));
    }

    public void updateProduct(Integer id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElse(null);
        Brand brand = brandRepository.findById(product.getBrand().getId()).orElse(null);
        Category category = categoryRepository.findById(product.getCategory().getId()).orElse(null);
        if (productToUpdate != null) {
            productToUpdate.setName(product.getName());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setQuantity(product.getQuantity());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setBrand(brand);
            productToUpdate.setCategory(category);
            productRepository.save(productToUpdate);
        }
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }


}
