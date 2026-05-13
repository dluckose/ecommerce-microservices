package com.ecommerce.projragai.config;

import com.ecommerce.projragai.dto.ProductMessage;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.boot.CommandLineRunner;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "product_queue";
    public static final String EXCHANGE = "product_exchange";
    public static final String ROUTING_KEY = "product_routing_key";

    @Bean
    public Queue queue() { return new Queue(QUEUE, true); }

    @Bean
    public TopicExchange exchange() { return new TopicExchange(EXCHANGE); }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }


    @Bean
    public CommandLineRunner verifyRabbitSetup(AmqpAdmin admin) {
        return args -> {
            System.out.println("---------------------------------------");
            var props = admin.getQueueProperties(QUEUE);
            if (props != null) {
                System.out.println("✅ SUCCESS: Queue '" + QUEUE + "' exists!");
                System.out.println("📊 Messages waiting: " + props.get("QUEUE_MESSAGE_COUNT"));
                System.out.println("👥 Consumers active: " + props.get("QUEUE_CONSUMER_COUNT"));
            } else {
                System.out.println("❌ ERROR: Queue not found on broker.");
            }
            System.out.println("---------------------------------------");
        };
    }
    @Bean
    public DefaultClassMapper classMapper() {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        // Map the sender's class name to YOUR local class
        idClassMapping.put("com.ecommerce.productservice.dto.ProductMessage", ProductMessage.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setClassMapper(classMapper());
        return converter;
    }
}