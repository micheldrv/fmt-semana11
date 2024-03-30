package com.darochavieira.fmtsemana11.dto;

import java.time.Instant;

public record JwtDto (
        String accessToken,
        Instant expiresAt
) {
}
