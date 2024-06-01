package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Brand;
import com.ppp_microservice_ecommerce.products.repository.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Brand> getAllBrands(
            Pageable pageable
    ) {
        return brandRepository.findAll(pageable);
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
