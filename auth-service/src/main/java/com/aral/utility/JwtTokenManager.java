package com.aral.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    private final String keypassword = "123456";

    public Optional<String> createToken(Long id){

        String token;
        Long expDate = 1000L*60*5;
        try {
            token = JWT.create()
                    .withAudience()
                    .withClaim("id",id)
                    .withIssuer("aral")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+expDate))
                    .sign(Algorithm.HMAC512(keypassword));
            return Optional.of(token);
        }catch (Exception exception){
            return Optional.empty();
        }

    }
}
