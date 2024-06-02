package com.ppp_microservice_ecommerce.products.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateBrandDto {
    String name;
    String description;
    MultipartFile image;

}
