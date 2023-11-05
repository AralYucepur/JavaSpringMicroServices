package com.aral.rabbitmq.consumer;

import com.aral.rabbitmq.model.CreateUser;
import com.aral.repository.entity.UserProfile;
import com.aral.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserProfileService userProfileService;


    @RabbitListener(queues = "queue-auth-create-user")
    public void createUserFromHandleQue(CreateUser createUser){
        userProfileService.save(UserProfile.builder()
                .authid(createUser.getAuthid())
                .email(createUser.getEmail())
                .username(createUser.getUsername()).build());
    }
}
