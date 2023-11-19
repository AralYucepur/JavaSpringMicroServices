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
    private String keyMail = "key-mail";
    private String queueMail = "queue-mail";

    @Bean
    DirectExchange exchangeAuth(){
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    Queue queueCreateUser(){
        return new Queue(queueAuth);
    }
    @Bean
    Queue queueMail(){return new Queue(queueMail);}


    @Bean
    public Binding bindingCreateUser(final Queue queueCreateUser, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueCreateUser).to(exchangeAuth).with(keyAuth);
    }
    @Bean
    public Binding bindingMail(final Queue queueMail, final DirectExchange exchangeAuth){
        return BindingBuilder.bind(queueMail).to(exchangeAuth).with(keyMail);
    }

}
