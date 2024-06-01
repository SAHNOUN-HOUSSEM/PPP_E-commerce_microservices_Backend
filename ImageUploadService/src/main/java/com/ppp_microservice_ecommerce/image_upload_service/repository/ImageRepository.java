package com.ppp_microservice_ecommerce.image_upload_service.repository;



import com.ppp_microservice_ecommerce.image_upload_service.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
}