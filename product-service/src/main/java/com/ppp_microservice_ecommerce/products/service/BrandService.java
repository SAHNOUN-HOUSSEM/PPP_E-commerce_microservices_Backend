package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.products.dto.CreateBrandDto;
import com.ppp_microservice_ecommerce.products.dto.ImageModel;
import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Brand;
import com.ppp_microservice_ecommerce.products.repository.BrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final ImageService imageService;

    public BrandService(BrandRepository brandRepository, ImageService imageService) {
        this.brandRepository = brandRepository;
        this.imageService = imageService;
    }

    public void createBrand(CreateBrandDto brandDto) {
        ImageModel imageModel = new ImageModel();
        imageModel.setName(brandDto.getName());
        imageModel.setFile(brandDto.getImage());
        Map<String, String> response = imageService.uploadImage(imageModel).getBody();
        Brand brand = Brand.builder()
                .name(brandDto.getName())
                .image(response.get("url"))
                .build();
        brandRepository.saveAndFlush(brand);
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

    public void deleteBrand(Integer id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand == null) {
            return;
        }
        brandRepository.delete(brand);
    }

    public void updateBrand(Integer id, CreateBrandDto brandDto) {
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand == null) {
            return;
        }
        ImageModel imageModel = new ImageModel();
        imageModel.setName(brandDto.getName());
        imageModel.setFile(brandDto.getImage());
        Map<String, String> response = imageService.uploadImage(imageModel).getBody();
        brand.setName(brandDto.getName());
        brand.setImage(response.get("url"));
        brandRepository.saveAndFlush(brand);
    }
}
