package com.ppp_microservice_ecommerce.clients.notifications;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("user")
public class UserNotificationConfig {

    private final String internalExchange = "internal.exchange";

    private final String userNotificationQueue = "user.notification.queue";

    private final String internalUserNotificationsRoutingKey = "internal.user.notification.routing-key";


    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(internalExchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(userNotificationQueue);
    }

    @Bean
    public Binding internalToNotificationBiding(){
        return BindingBuilder.bind(notificationQueue()).to(internalTopicExchange()).with(internalUserNotificationsRoutingKey);
    }

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificationQueue() {
        return userNotificationQueue;
    }

    public String getInternalNotificationsRoutingKey() {
        return internalUserNotificationsRoutingKey;
    }
}
