package com.ppp_microservice_ecommerce.image_upload_service.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageModel {
    private String name;
    private MultipartFile file;
}