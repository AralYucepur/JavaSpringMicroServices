package com.aral.rabbitmq.producer;

import com.aral.rabbitmq.model.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer {

    private final RabbitTemplate rabbitTemplate;

    public void convertAndSendMessageCreateUser(CreateUser createUser){
        rabbitTemplate.convertAndSend("exchange-auth", "key-auth", createUser);
    }
}
