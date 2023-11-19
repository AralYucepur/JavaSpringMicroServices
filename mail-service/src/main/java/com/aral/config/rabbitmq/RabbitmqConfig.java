package com.aral.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String queueMail = "queue-mail";
    @Bean
    Queue queueMail (){return new Queue(queueMail);}
}
