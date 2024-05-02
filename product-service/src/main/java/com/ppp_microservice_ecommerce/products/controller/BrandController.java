package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.products.entites.Brand;
import com.ppp_microservice_ecommerce.products.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brand")
@Slf4j
public class BrandController {
    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }
    @PostMapping("/create")
    public void createBrand(@RequestBody Brand brand) {
        log.info("new brand creation {} ", brand);
        brandService.createBrand(brand);
    }
}
