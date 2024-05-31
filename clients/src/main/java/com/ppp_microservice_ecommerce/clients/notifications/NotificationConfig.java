package com.ppp_microservice_ecommerce.clients.notifications;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class    NotificationConfig {

    private final String internalExchange = "internal.exchange";

    private final String notificationQueue = "notification.queue";

    private final String internalNotificationsRoutingKey = "internal.notification.routing-key";


    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(internalExchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(notificationQueue);
    }

    @Bean
    public Binding internalToNotificationBiding(){
        return BindingBuilder.bind(notificationQueue()).to(internalTopicExchange()).with(internalNotificationsRoutingKey);
    }

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificationQueue() {
        return notificationQueue;
    }

        public String getInternalNotificationsRoutingKey() {
        return internalNotificationsRoutingKey;
    }
}
