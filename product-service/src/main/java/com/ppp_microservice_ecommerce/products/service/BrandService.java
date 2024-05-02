package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.products.entites.Brand;
import com.ppp_microservice_ecommerce.products.repository.BrandRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void createBrand(Brand brand) {
        brandRepository.save(brand);
    }
}