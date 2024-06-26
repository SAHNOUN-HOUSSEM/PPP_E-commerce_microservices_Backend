package com.ppp_microservice_ecommerce.products.repository;

import com.ppp_microservice_ecommerce.products.entities.Category;
import com.ppp_microservice_ecommerce.products.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    List<Product> findByIdIn(List<Integer> ids);
    List<Product> findByCategory(Category category);
    Page<Product> findByCategory(Category category, Pageable pageable);
}
