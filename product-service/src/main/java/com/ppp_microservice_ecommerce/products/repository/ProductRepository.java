package com.ppp_microservice_ecommerce.products.repository;

import com.ppp_microservice_ecommerce.products.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
    List<Product> findByIdIn(List<Integer> ids);
}
