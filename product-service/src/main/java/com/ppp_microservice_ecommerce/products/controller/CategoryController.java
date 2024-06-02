package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.clients.auth.*;
import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Category;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    private final AuthClient authClient;

    @PostMapping()
    public void createCategory(@RequestBody Category category, @RequestHeader("Authorization") String BearerToken) {
        System.out.println("BearerToken = " + BearerToken);
        String token = BearerToken.substring(7);
        ValidateTokenDto validateTokenDto = new ValidateTokenDto(token);
        Boolean isValid = authClient.validateToken(validateTokenDto);
        if (!isValid) {
            throw new RuntimeException("Invalid token");
        }
        System.out.println("Token is valid");
        System.out.println("token = " + token);
        GetUserFromTokenDto getUserFromTokenDto = new GetUserFromTokenDto(token);
        AppUser user = authClient.getUserFromToken(getUserFromTokenDto);
        System.out.println("user = " + user);
        if(user.getRole().equals(AppUserRoles.USER)){
            throw new RuntimeException("Unauthorized");
        }
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
