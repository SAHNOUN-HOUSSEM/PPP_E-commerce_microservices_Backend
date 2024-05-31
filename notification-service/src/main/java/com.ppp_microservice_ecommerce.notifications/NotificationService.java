package com.ppp_microservice_ecommerce.notifications;


import com.ppp_microservice_ecommerce.clients.notifications.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void sendNotification(NotificationRequest notificationRequest) {
        System.out.println("saving");
        Notification notification = Notification.builder()
                        .orderId(notificationRequest.getOrderID())
                                .message(notificationRequest.getMessage())
                                                .build();
        notificationRepository.save(notification);
        System.out.println("saved");
    }
}
