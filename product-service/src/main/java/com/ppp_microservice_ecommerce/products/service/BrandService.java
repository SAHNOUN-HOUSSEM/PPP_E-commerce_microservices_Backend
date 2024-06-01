package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Brand;
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

    public Iterable<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Brand getBrand(Integer id) {
        return brandRepository.findById(id).orElse(null);
    }

    public Iterable<ProductDTO> getBrandProducts(Integer id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand == null) {
            return null;
        }
        return brand.getProducts().stream().map(product -> new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(),product.getQuantity(),product.getImage())).toList();
    }
}
