package com.ppp_microservice_ecommerce.api_gateway.config;

import com.ppp_microservice_ecommerce.api_gateway.filter.RouteValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
