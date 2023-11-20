package com.aral.service;

import com.aral.rabbitmq.model.MailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendMail (MailModel model){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("${aral_gmail}");
        simpleMailMessage.setTo(model.getEmail());
        simpleMailMessage.setSubject("Aktivasyon Kodunuz.");
        simpleMailMessage.setText(model.getEmail()+" mail adresine ait aktivasyon kodunuz: "+model.getActivationCode());
        javaMailSender.send(simpleMailMessage);
        System.out.println("Aktivasyon kodu gönderildi.");
    }
}
