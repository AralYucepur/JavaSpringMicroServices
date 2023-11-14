package com.aral.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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



    public Optional<Long> getByIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(keypassword);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("aral")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("id").asLong());
        }catch(Exception exception){
                return Optional.empty();
            }
    }
    public Optional<List<String>> getRolesFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(keypassword);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("aral")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("roles").asList(String.class));
        }catch(Exception exception){
            return Optional.empty();
        }
    }
}
