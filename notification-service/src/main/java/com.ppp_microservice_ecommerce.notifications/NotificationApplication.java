package com.ppp_microservice_ecommerce.notifications;

import com.ppp_microservice_ecommerce.amqp.RabbitMQMessageProducer;
import com.ppp_microservice_ecommerce.clients.notifications.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages= {
                "com.ppp_microservice_ecommerce.notifications",
                "com.ppp_microservice_ecommerce.amqp",
                "com.ppp_microservice_ecommerce.clients.notifications"
        }
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig config) {
//        return args -> {
//            producer.publish("Hello World", config.getInternalExchange(), config.getInternalNotificationsRoutingKey());
//        };
//    }
}
