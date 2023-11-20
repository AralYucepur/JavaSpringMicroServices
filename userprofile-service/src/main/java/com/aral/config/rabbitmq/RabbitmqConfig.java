package com.aral.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String queueAuth = "queue-auth-create-user";
    @Bean
    Queue queueCreateUser(){
        return new Queue(queueAuth);
    }

}
