package com.darochavieira.fmtsemana11.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.darochavieira.fmtsemana11.dto.JwtDto;
import com.darochavieira.fmtsemana11.service.interfaces.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public JwtDto gerarToken(String email) {
        try {
            Instant expiresAt = gerarExpiracao();
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String jwtToken = JWT.create()
                    .withIssuer(issuer)
                    .withSubject(email)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);

            return new JwtDto(jwtToken, expiresAt);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token.");
        }
    }

    @Override
    public String obterEmail(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    @Override
    public Instant gerarExpiracao() {
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-3"));
    }
}
