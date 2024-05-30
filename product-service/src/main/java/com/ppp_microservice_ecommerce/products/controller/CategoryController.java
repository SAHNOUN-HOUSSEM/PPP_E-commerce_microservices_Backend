package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Category;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping()
    public void createCategory(@RequestBody Category category) {
//        log.info("new category creation");
        categoryService.createCategory(category);
    }

    @GetMapping()
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("{id}/products")
    public List<ProductDTO> getCategoryProducts(@PathVariable int id) {
        return categoryService.getCategoryProducts(id);
    }
}
