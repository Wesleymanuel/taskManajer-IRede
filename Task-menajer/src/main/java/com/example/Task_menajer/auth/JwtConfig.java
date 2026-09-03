package com.example.Task_menajer.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Task_menajer.DTOs.JwtUserData;
import com.example.Task_menajer.domain.entitys.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Component
public class JwtConfig {

    private final String secret = "secret";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("API task manajer")
                    .withSubject(user.getUsername())
                    .withClaim("userId", user.getUser_id().toString())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public Optional<JwtUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("API task manajer")
                    .build()
                    .verify(token);

            String userId = decodedJWT
                    .getClaim("userId")
                    .asString();

            String email = decodedJWT.getSubject();

            return Optional.of(
                    JwtUserData.builder()
                            .userId(userId)
                            .email(email)
                            .build()
            );

        } catch (JWTVerificationException exception) {
            return Optional.empty();
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}