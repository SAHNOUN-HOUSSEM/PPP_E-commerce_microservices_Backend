package com.ppp_microservice_ecommerce.clients.notifications;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
// doubtful
@Profile("notification")
public class OrderNotificationConfig {

    private final String internalExchange = "internal.exchange";

    private final String orderNotificationQueue = "order.notification.queue";

    private final String internalOrderNotificationsRoutingKey = "internal.order.notification.routing-key";


    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(internalExchange);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(orderNotificationQueue);
    }

    @Bean
    public Binding internalToNotificationBiding(){
        return BindingBuilder.bind(notificationQueue()).to(internalTopicExchange()).with(internalOrderNotificationsRoutingKey);
    }

    public String getInternalExchange() {
        return internalExchange;
    }

    public String getNotificationQueue() {
        return orderNotificationQueue;
    }

        public String getInternalNotificationsRoutingKey() {
        return internalOrderNotificationsRoutingKey;
    }
}
