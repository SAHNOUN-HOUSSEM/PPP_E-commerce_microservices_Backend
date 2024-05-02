package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.products.entites.Category;
import com.ppp_microservice_ecommerce.products.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }
}
