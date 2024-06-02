package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.clients.auth.*;
import com.ppp_microservice_ecommerce.products.dto.CreateCategoryDto;
import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Brand;
import com.ppp_microservice_ecommerce.products.entities.Category;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    private final AuthClient authClient;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void createCategory(@ModelAttribute CreateCategoryDto createCategoryDto
                               //        , @RequestHeader("Authorization") String BearerToken
    ) {
        /*
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
        */
        categoryService.createCategory(createCategoryDto);
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
    public Page<ProductDTO> getCategoryProducts(@PathVariable int id,
                                                @RequestParam(defaultValue = "0") Integer page,
                                                @RequestParam(defaultValue = "10") Integer size,
                                                @RequestParam(defaultValue = "id") String sortBy,
                                                @RequestParam(defaultValue = "") String search
                                                )
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return categoryService.getCategoryProducts(id, pageable,search);
    }

//    @GetMapping("{id}/brands")
//    public List<Brand> getBrandsByCategory(@PathVariable int id) {
//        return categoryService.getBrandsByCategory(id);
//    }
}
