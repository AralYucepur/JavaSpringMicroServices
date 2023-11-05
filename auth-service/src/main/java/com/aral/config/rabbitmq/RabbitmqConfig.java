package com.aral.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String exchangeAuth = "exchange-auth";
    private String keyAuth = "key-auth";
    private String queueAuth = "queue-auth-create-user";


    @Bean
    Queue queueCreateUser(){
        return new Queue(queueAuth);
    }
    @Bean
    DirectExchange exchangeAuth(){
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    public Binding bindingCreateUser(final Queue queueCreateUser, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueCreateUser).to(exchangeAuth).with(keyAuth);
    }

}
