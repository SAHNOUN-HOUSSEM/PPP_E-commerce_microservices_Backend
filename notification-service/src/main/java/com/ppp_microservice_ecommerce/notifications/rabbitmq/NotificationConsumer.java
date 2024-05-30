package com.ppp_microservice_ecommerce.notifications.rabbitmq;


import com.ppp_microservice_ecommerce.notifications.NotificationService;
import com.ppp_microservice_ecommerce.notifications.Notification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")

    public void consumeNotification(String s) {
        log.info("Consuming notification");
    }
}
