package com.ppp_microservice_ecommerce.products.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductDto{
    String name;
    String description;
    Double price;
    Integer quantity;
    MultipartFile image;
    Integer categoryId;
    Integer brandId;
}
