package com.aral.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateProfileRequestDto {
    @NotBlank
    Long authid;
    @NotBlank
    String username;
    @NotBlank
    @Email
    String email;
    String token;
}
