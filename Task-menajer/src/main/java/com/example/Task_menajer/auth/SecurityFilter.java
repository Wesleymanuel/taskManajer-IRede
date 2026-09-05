package com.example.Task_menajer.auth;

import com.example.Task_menajer.DTOs.JwtUserData;
import com.example.Task_menajer.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final UserRepository userRepository; // 1. Injete o repositório

    public SecurityFilter(JwtConfig jwtConfig, UserRepository userRepository) {
        this.jwtConfig = jwtConfig;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            String token = authorizationHeader.substring(7);

            Optional<JwtUserData> authorized = jwtConfig.validateToken(token);

            if (authorized.isPresent()) {
                JwtUserData userData = authorized.get();

                // 2. Busque o UserDetails real no banco pelo e-mail que veio no token
                Optional<UserDetails> userOptional = userRepository.findUsersByEmail(userData.email());

                if (userOptional.isPresent()) {
                    UserDetails user = userOptional.get();

                    // 3. Passe o objeto 'user' e as authorities reais dele no construtor
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    null,
                                    user.getAuthorities()
                            );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}