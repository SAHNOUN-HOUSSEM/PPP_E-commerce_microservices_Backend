package com.ppp_microservice_ecommerce.notifications;


import com.ppp_microservice_ecommerce.clients.notifications.OrderNotificationRequest;
import com.ppp_microservice_ecommerce.clients.notifications.UserNotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Profile("notification")
    @RabbitListener(queues = {"order.notification.queue"})
    public void sendOrderNotification(OrderNotificationRequest orderNotificationRequest) {
        System.out.println("saving order");
        Notification notification = Notification.builder()
                        .originId(orderNotificationRequest.getOrderID())
                                .message(orderNotificationRequest.getMessage())
                                                .build();
        notificationRepository.save(notification);
        System.out.println("saved");

        // emit event to client

    }


    @Profile("notification")
    @RabbitListener(queues = {"user.notification.queue"})
    public void sendUserNotification(UserNotificationRequest userNotificationRequest) {
        System.out.println("saving user");
        Notification notification = Notification.builder()
                .originId(userNotificationRequest.getUserID())
                .message(userNotificationRequest.getMessage())
                .build();
        notificationRepository.save(notification);
        System.out.println("saved");

        // emit event to client

    }
}
