package com.aral.service;

import com.aral.repository.IUserProfileRepository;
import com.aral.repository.entity.UserProfile;
import com.aral.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {
    private final IUserProfileRepository repository;

    public UserProfileService(IUserProfileRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public UserProfile findByUsername(String username){
        Optional<UserProfile> result = repository.findOptionalByUsername(username);
        if(result.isEmpty()) return UserProfile.builder().build();
        return result.get();

    }
}
