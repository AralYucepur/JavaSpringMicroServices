package com.aral.rabbitmq.consumer;

import com.aral.rabbitmq.model.MailModel;
import com.aral.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailConsumer {

    private final MailService mailService;

    @RabbitListener(queues = "queue-mail")
    public void sendMail(MailModel mailModel){
        mailService.sendMail(mailModel);
    }
}
