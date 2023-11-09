package com.aral.utility;

import com.aral.dto.request.UserProfileSaveRequestDto;
import com.aral.manager.IUserProfileElasticService;
import com.aral.repository.IUserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElasticData {

    private final IUserProfileRepository iUserProfileRepository;
    private final IUserProfileElasticService iUserProfileElasticService;

//    @PostConstruct
    public void init(){
        iUserProfileRepository.findAll().forEach(u->{
            iUserProfileElasticService.save(UserProfileSaveRequestDto.builder()
                    .userid(u.getId())
                    .authid(u.getAuthid())
                    .username(u.getUsername())
                    .profileimage(u.getProfileimage())
                    .build());
        });
    }
}
