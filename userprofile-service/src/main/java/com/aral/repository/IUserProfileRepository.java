package com.aral.repository;


import com.aral.repository.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserProfileRepository extends MongoRepository<UserProfile,String> {



}
