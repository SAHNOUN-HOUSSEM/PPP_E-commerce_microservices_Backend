package com.ppp_microservice_ecommerce.notifications;


import com.ppp_microservice_ecommerce.clients.notifications.OrderNotificationRequest;
import com.ppp_microservice_ecommerce.clients.notifications.UserNotificationRequest;
import email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

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

    }


    @Profile("notification")
    @RabbitListener(queues = {"user.notification.queue"})
    public void sendUserNotification(UserNotificationRequest userNotificationRequest) throws MessagingException {
        System.out.println("saving user");
        Notification notification = Notification.builder()
                .originId(userNotificationRequest.getUserID())
                .message(userNotificationRequest.getMessage())
                .build();
        notificationRepository.save(notification);
        System.out.println("saved");

        // send registration confirmation to user email
        emailService.sendRegistrationEmail(userNotificationRequest.getEmail(), userNotificationRequest.getUsername());

    }
}
