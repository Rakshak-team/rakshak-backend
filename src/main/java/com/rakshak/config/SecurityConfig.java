/**
 * ============================================================================
 * File: SecurityConfig.java
 * Path: src/main/java/com/rakshak/config/SecurityConfig.java
 * ----------------------------------------------------------------------------
 * Description:
 * Spring Security configuration for the Rakshak backend.
 *
 * Responsibilities:
 * • Configure application security
 * • Configure JWT authentication
 * • Register PasswordEncoder bean
 * • Configure stateless session
 * • Configure public and protected endpoints
 * ============================================================================
 */

package com.rakshak.config;

// Imports

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rakshak.security.JwtAuthenticationFilter;

// ============================================================================
// Security Configuration
// ============================================================================

@Configuration
public class SecurityConfig {

    // =========================================================================
    // Dependencies
    // =========================================================================

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // =========================================================================
    // Password Encoder
    // =========================================================================

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // =========================================================================
    // Security Filter Chain
    // =========================================================================

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http

                // Disable CSRF
                .csrf(csrf -> csrf.disable())

                // Enable CORS
                .cors(Customizer.withDefaults())

                // Stateless Session
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // Authorization Rules
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                		.requestMatchers(

                		        // Authentication APIs
                		        "/auth/**",

                		        // Swagger / OpenAPI
                		        "/swagger-ui/**",
                		        "/swagger-ui.html",
                		        "/v3/api-docs/**",
                		        "/v3/api-docs.yaml",

                		        // Swagger Resources
                		        "/swagger-resources/**",
                		        "/webjars/**"
                		         
                		     
                		        
                		        

                		).permitAll()

                        // All other APIs require authentication
                        .anyRequest()
                        .authenticated()

                )

                // JWT Filter
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

}