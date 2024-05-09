package com.ppp_microservice_ecommerce.products.repository;

import com.ppp_microservice_ecommerce.products.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
}
