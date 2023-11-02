package com.aral.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoLoginRequestDto {
    @NotBlank(message = "Kullanıcı adı boş geçilmez.")
    @Size(min = 3,max = 32)
    String username;
    @NotBlank(message = "Şifre boş geçilmez.")
    @Size(min = 3,max = 64)
    String password;
}
