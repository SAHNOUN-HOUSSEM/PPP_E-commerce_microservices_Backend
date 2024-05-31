package com.ppp_microservice_ecommerce.notifications.rabbitmq;


import com.ppp_microservice_ecommerce.clients.notifications.NotificationRequest;
import com.ppp_microservice_ecommerce.notifications.NotificationService;
import com.ppp_microservice_ecommerce.notifications.Notification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
@Profile("notification")

public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues   = {"notification.queue"})
    // NotificationRequest notificationRequest
    public void consumeNotification(NotificationRequest notificationRequest){
        log.info("Consuming notification");
        notificationService.sendNotification(notificationRequest);
        log.info("Notification sent");
    }
}
