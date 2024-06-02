package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.clients.auth.*;
import com.ppp_microservice_ecommerce.clients.orders.OrderRequest;
import com.ppp_microservice_ecommerce.products.dto.ProductResponse;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final AuthClient authClient;

    @GetMapping()
    public Page<Product> getProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        log.info("Getting product");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        log.info(pageable.toString());
        return productService.getProducts(pageable);
    }

    @PostMapping("/names")
    public Map<Integer,String> getProductsByIds(@RequestBody Set<Integer> productIDs) {
        log.info("Getting products by ids");
        return productService.getProductNames(productIDs);
    }

    @PostMapping("/ids")
    public List<ProductResponse> getProductsByIds(@RequestBody OrderRequest order) {
        log.info("Getting products by ids");
        return productService.areInStock(order);
    }

    @PostMapping("/update")
    public void updateStock(@RequestBody OrderRequest order
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
        log.info("Updating product stock");
        productService.updateStock(order);
    }

    @PostMapping()
    public void createProduct(@RequestBody Product product
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
        if(user.getRole().equals("USER")){
            throw new RuntimeException("Unauthorized");
        }
        System.out.println(product.getId());

         */
        log.info("Creating product");
        productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        log.info("Getting product by id");
        return productService.getProduct(id);
    }

    @PostMapping("/lists")
    public List<Product> getProductsByIds(@RequestBody List<Integer> ids) {
        log.info("Getting product by ids");
        return productService.getProductsByIds(ids);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        log.info("Updating product");
        productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        log.info("Deleting product");
        productService.deleteProduct(id);
    }

}
