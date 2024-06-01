package com.ppp_microservice_ecommerce.products.controller;

import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Brand;
import com.ppp_microservice_ecommerce.products.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brand")
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
    public Page<Brand> getAllBrands(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        log.info("Getting brand");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return brandService.getAllBrands(pageable);
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
