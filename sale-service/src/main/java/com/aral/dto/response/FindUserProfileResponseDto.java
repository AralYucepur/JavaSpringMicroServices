package com.aral.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FindUserProfileResponseDto {

    Long authid;
    String username;
    String email;
    String phone;
    String address;
    String profileimage;
    Double balance;
}
