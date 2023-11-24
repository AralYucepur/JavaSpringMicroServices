package com.aral.repository;


import com.aral.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile,String> {

    // Check parameter!!!
    Optional<UserProfile> findOptionalByAuthid(Long id);
}
