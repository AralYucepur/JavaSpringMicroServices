package com.aral.utility;

import com.aral.repository.enums.ERole;
import com.aral.repository.enums.EState;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    private final String keypassword = "123456";

    public Optional<String> createToken(Long id, ERole role, EState state){

        String token;
        Long expDate = 1000L*60*5;
        try {
            token = JWT.create()
                    .withAudience()
                    .withClaim("id",id)
                    .withClaim("role", role.toString())
                    .withClaim("state", state.toString())
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

    public Optional<String> getRoleFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(keypassword);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("aral")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("role").asString());
        }catch(Exception exception){
            return Optional.empty();
        }
    }
    public Optional<String> getStateFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(keypassword);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("aral")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT == null)
                return Optional.empty();
            return Optional.of(decodedJWT.getClaim("state").asString());
        }catch(Exception exception){
            return Optional.empty();
        }
    }
}
