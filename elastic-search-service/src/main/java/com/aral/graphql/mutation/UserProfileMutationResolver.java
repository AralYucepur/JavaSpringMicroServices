package com.aral.graphql.mutation;

import com.aral.graphql.model.UserProfileInput;
import com.aral.repository.entity.UserProfile;
import com.aral.service.UserProfileService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProfileMutationResolver implements GraphQLMutationResolver {
    private final UserProfileService userProfileService;

    public Boolean createUserProfile(UserProfileInput input){
        userProfileService.save(UserProfile.builder()
                .userid(input.getUserid())
                .authid(input.getAuthid())
                .username(input.getUsername())
                .profileimage(input.getProfileimage())
                .build());
        return true;
    }
}
