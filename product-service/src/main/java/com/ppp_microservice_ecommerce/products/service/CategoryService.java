package com.ppp_microservice_ecommerce.products.service;

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
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public void createCategory(Category category) {
        categoryRepository.save(category);
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

//    public List<Brand> getBrandsByCategory(Integer categoryId) {
//        Category category = categoryRepository.findById(categoryId).orElse(null);
//        if(category == null) {
//            return null;
//        }
//        List<Product> products = productRepository.findByCategory(category);
//        return products.stream()
//                .map(Product::getBrand)
//                .distinct()
//                .collect(Collectors.toList());
//    }
}
