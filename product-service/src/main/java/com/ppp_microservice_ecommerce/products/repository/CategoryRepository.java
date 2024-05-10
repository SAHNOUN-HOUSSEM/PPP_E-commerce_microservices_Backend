package com.ppp_microservice_ecommerce.products.repository;

import com.ppp_microservice_ecommerce.products.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
