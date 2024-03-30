package com.darochavieira.fmtsemana11.config;

import com.darochavieira.fmtsemana11.service.AuthServiceImpl;
import com.darochavieira.fmtsemana11.service.interfaces.JwtService;
import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@NonNullApi
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = extrairToken(request);

        if (jwtToken != null) {
            String email = jwtService.obterEmail(jwtToken);
            UserDetails user = userDetailsService.loadUserByUsername(email);

            var authToken = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    user.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    @Nullable
    private String extrairToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null) return null;
        if (!header.startsWith("Bearer ")) return null;

        return header.substring(7);
    }
}
