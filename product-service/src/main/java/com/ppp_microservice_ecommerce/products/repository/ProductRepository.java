package com.ppp_microservice_ecommerce.products.repository;

import com.ppp_microservice_ecommerce.products.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{

}
