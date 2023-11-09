package com.aral.graphql.model;

import lombok.Data;

@Data
public class UserProfileInput {

    String userid;
    Long authid;
    String username;
    String profileimage;

}
