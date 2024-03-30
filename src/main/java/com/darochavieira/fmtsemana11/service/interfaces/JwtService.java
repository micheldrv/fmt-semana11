package com.darochavieira.fmtsemana11.service.interfaces;

import com.auth0.jwt.algorithms.Algorithm;
import com.darochavieira.fmtsemana11.dto.JwtDto;
import com.darochavieira.fmtsemana11.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;

public interface JwtService {
    JwtDto gerarToken(String email);
    String obterEmail(String token);
    Instant gerarExpiracao();
}
