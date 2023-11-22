package com.aral.repository.entity;

import com.aral.utility.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class UserProfile extends BaseEntity {

    @Id
    String id;
    Long authid;
    String username;
    String email;
    String phone;
    String address;
    String profileimage;
    @Builder.Default
    Double balance = 0.0;

}
