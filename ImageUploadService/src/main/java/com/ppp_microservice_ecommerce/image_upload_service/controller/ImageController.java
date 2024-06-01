package com.ppp_microservice_ecommerce.image_upload_service.controller;

import com.ppp_microservice_ecommerce.image_upload_service.dto.ImageModel;
import com.ppp_microservice_ecommerce.image_upload_service.repository.ImageRepository;
import com.ppp_microservice_ecommerce.image_upload_service.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Map> upload(ImageModel imageModel) {
        try {
            return imageService.uploadImage(imageModel);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}