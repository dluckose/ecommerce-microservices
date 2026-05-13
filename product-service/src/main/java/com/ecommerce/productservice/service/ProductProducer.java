package com.ecommerce.productservice.service;

import com.ecommerce.productservice.config.RabbitMQProducerConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendProductUpdate(Object product) {
        rabbitTemplate.convertAndSend(RabbitMQProducerConfig.EXCHANGE, RabbitMQProducerConfig.ROUTING_KEY, product);
        System.out.println("Sent to RabbitMQ!");
    }
}
