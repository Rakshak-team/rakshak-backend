/**
 * ============================================================================
 * File: CustomUserDetailsService.java
 * Path: src/main/java/com/rakshak/security/CustomUserDetailsService.java
 * ----------------------------------------------------------------------------
 * Description:
 * Loads user details from the database for Spring Security authentication.
 *
 * Responsibilities:
 * • Load user by email
 * • Convert User entity to CustomUserDetails
 * • Throw exception if user does not exist
 * ============================================================================
 */

package com.rakshak.security;

// Imports

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rakshak.entity.User;
import com.rakshak.repository.UserRepository;

// ============================================================================
// Custom User Details Service
// ============================================================================

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // =========================================================================
    // Dependencies
    // =========================================================================

    @Autowired
    private UserRepository userRepository;
    // =========================================================================
    // Load User By Username (Email)
    // =========================================================================

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + email
                        )
                );

        return new CustomUserDetails(user);
    }

}