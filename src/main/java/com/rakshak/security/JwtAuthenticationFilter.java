/**
 * ============================================================================
 * File: JwtAuthenticationFilter.java
 * Path: src/main/java/com/rakshak/security/JwtAuthenticationFilter.java
 * ----------------------------------------------------------------------------
 * Description:
 * JWT Authentication Filter responsible for authenticating incoming requests
 * using JSON Web Tokens.
 *
 * Responsibilities:
 * • Read JWT token from Authorization header
 * • Validate JWT token
 * • Load authenticated user
 * • Set authentication into Spring Security Context
 * • Continue request processing
 * ============================================================================
 */

package com.rakshak.security;

// Imports

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// ============================================================================
// JWT Authentication Filter
// ============================================================================

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // =========================================================================
    // Dependencies
    // =========================================================================

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;
    // =========================================================================
    // Filter Request
    // =========================================================================

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Read Authorization Header
        final String authHeader = request.getHeader("Authorization");

        final String jwtToken;
        final String userEmail;

        // If Authorization header is missing or invalid, continue request
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        // Extract JWT Token
        jwtToken = authHeader.substring(7);

        // Extract email from JWT
        userEmail = jwtService.extractEmail(jwtToken);

        // Authenticate only if user is not already authenticated
        if (userEmail != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(userEmail);

            // Validate JWT
            if (jwtService.isTokenValid(jwtToken,
                    (com.rakshak.entity.User) userDetails)) {

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authenticationToken);
            }
        }

        // Continue request processing
        filterChain.doFilter(request, response);
    }
    // =========================================================================
    // End of Filter
    // =========================================================================

}