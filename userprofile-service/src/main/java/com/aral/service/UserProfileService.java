package com.aral.service;

import com.aral.dto.request.CreateProfileRequestDto;
import com.aral.repository.IUserProfileRepository;
import com.aral.repository.entity.UserProfile;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {

    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Boolean createProfile(CreateProfileRequestDto dto){

        repository.save(UserProfile.builder()
                .authid(dto.getAuthid())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build());
        return true;

    }



}
