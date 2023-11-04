package com.aral.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType{

    INTERNAL_ERROR(5100,"Sunucuda beklenmeyen hata oluştu",HttpStatus.INTERNAL_SERVER_ERROR),
    REGISTER_USED_USERNAME_ERROR(4110,"Kullanıcı adı başkası tarafından kullanılıyor.",HttpStatus.BAD_REQUEST),
    REGISTER_UNMATCHED_REPASSWORD_ERROR(4111,"Şifre ve şifre tekrarı aynı olmalı.",HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4112,"Kullanıcı adı veya şifre hatalı.",HttpStatus.BAD_REQUEST),
    JWT_TOKEN_CREATE_ERROR(4113,"Token oluşturulamadı,",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(4100,"Parametre eksik veya hatalı",HttpStatus.BAD_REQUEST),

    JWT_INVALID_TOKEN(4114,"Geçersiz token.",HttpStatus.BAD_REQUEST)

    ;


    int code;
    String message;
    HttpStatus httpStatus;

}
