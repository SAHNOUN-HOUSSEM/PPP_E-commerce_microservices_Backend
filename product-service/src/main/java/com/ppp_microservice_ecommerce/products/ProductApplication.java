package com.ppp_microservice_ecommerce.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.ppp_microservice_ecommerce.products",
                "com.ppp_microservice_ecommerce.amqp",
                "com.ppp_microservice_ecommerce.clients"
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = {
                "com.ppp_microservice_ecommerce.clients",
        }
)
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
