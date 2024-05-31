package com.ppp_microservice_ecommerce.authService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.ppp_microservice_ecommerce.authService",
                "com.ppp_microservice_ecommerce.amqp",
                "com.ppp_microservice_ecommerce.clients",
        }
)
@EnableEurekaClient
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args)   ;
    }
}
