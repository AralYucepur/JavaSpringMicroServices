package com.aral.rabbitmq.producer;

import com.aral.rabbitmq.model.MailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailProducer {

    private final RabbitTemplate rabbitTemplate;

    public void convertAndSendMessageMailModel(MailModel mailModel){
        rabbitTemplate.convertAndSend("exchange-auth", "key-mail", mailModel);
    }
}
