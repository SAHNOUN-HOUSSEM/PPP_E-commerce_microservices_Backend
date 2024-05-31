package com.ppp_microservice_ecommerce.orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

    @SpringBootApplication(
            scanBasePackages = {
                    "com.ppp_microservice_ecommerce.orders",
                    "com.ppp_microservice_ecommerce.amqp",
                    "com.ppp_microservice_ecommerce.clients",
            }
    )
@EnableEurekaClient
@EnableFeignClients(
        basePackages = {
                "com.ppp_microservice_ecommerce.clients",
        }
)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
