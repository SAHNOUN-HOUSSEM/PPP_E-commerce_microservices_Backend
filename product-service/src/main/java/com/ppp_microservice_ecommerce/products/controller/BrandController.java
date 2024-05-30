package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Brand;
import com.ppp_microservice_ecommerce.products.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
@Slf4j
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @PostMapping()
    public void createBrand(@RequestBody Brand brand) {
        log.info("new brand creation {} ", brand);
        brandService.createBrand(brand);
    }

    @GetMapping()
    public Iterable<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public Brand getBrand(@PathVariable Integer id) {
        return brandService.getBrand(id);
    }

    @GetMapping("{id}/products")
    public Iterable<ProductDTO> getBrandProducts(@PathVariable Integer id) {
        return brandService.getBrandProducts(id);
    }

}
