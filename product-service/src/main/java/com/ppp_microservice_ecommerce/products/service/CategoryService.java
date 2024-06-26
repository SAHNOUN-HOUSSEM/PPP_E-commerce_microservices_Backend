package com.ppp_microservice_ecommerce.products.service;

import com.ppp_microservice_ecommerce.products.dto.CreateCategoryDto;
import com.ppp_microservice_ecommerce.products.dto.ImageModel;
import com.ppp_microservice_ecommerce.products.dto.ProductDTO;
import com.ppp_microservice_ecommerce.products.entities.Brand;
import com.ppp_microservice_ecommerce.products.entities.Category;
import com.ppp_microservice_ecommerce.products.entities.Product;
import com.ppp_microservice_ecommerce.products.repository.CategoryRepository;
import com.ppp_microservice_ecommerce.products.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ImageService imageService;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository, ImageService imageService) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.imageService = imageService;
    }

    public void createCategory(CreateCategoryDto createCategoryDto) {
        ImageModel imageModel = new ImageModel();
        imageModel.setName(createCategoryDto.getName());
        imageModel.setFile(createCategoryDto.getImage());
        Map<String, String> response = imageService.uploadImage(imageModel).getBody();
        Category category = Category.builder()
                .name(createCategoryDto.getName())
                .description(createCategoryDto.getDescription())
                .image(response.get("url"))
                .build();
        categoryRepository.saveAndFlush(category);
    }

    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Page<ProductDTO> getCategoryProducts(Integer id, Pageable pageable,String search)
    {

        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }

        Page<Product> products = productRepository.findByCategory(category, pageable);

        List<ProductDTO> productDTOList = products.stream()
                .filter(product -> product.getName().contains(search))
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getImage()))
                .collect(Collectors.toList());

        return new PageImpl<>(productDTOList, pageable, products.getTotalElements());

//        return products.map(product -> new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getQuantity(), product.getImage()));

    }

    public void deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            return;
        }
        categoryRepository.delete(category);
    }

    public void updateCategory(Integer id, CreateCategoryDto createCategoryDto) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null) {
            return;
        }
//        ImageModel imageModel = new ImageModel();
//        imageModel.setName(createCategoryDto.getName());
//        imageModel.setFile(createCategoryDto.getImage());
//        Map<String, String> response = imageService.uploadImage(imageModel).getBody();
        category.setName(createCategoryDto.getName());
        category.setDescription(createCategoryDto.getDescription());
//        category.setImage(response.get("url"));
        categoryRepository.saveAndFlush(category);
    }
}
