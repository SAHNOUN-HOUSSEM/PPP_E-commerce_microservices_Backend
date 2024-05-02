package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.products.entites.Category;
import com.ppp_microservice_ecommerce.products.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
    public void createCategory(@RequestBody Category category) {
        log.info("new category creation");
        categoryService.createCategory(category);
    }
}
