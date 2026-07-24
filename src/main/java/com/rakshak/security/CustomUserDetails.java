/**
 * ============================================================================
 * File: CustomUserDetails.java
 * Path: src/main/java/com/rakshak/security/CustomUserDetails.java
 * ----------------------------------------------------------------------------
 * Description:
 * Custom implementation of Spring Security's UserDetails interface.
 *
 * Responsibilities:
 * • Wrap User entity
 * • Provide user information to Spring Security
 * • Supply authorities and account status
 * • Expose underlying User entity
 * ============================================================================
 */

package com.rakshak.security;

// Imports

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rakshak.entity.User;

// ============================================================================
// Custom User Details
// ============================================================================

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    // =========================================================================
    // User Entity
    // =========================================================================

    private final User user;

    // =========================================================================
    // Constructor
    // =========================================================================

    public CustomUserDetails(User user) {
        this.user = user;
    }

    // =========================================================================
    // Expose User Entity
    // =========================================================================

    public User getUser() {
        return user;
    }

    // =========================================================================
    // Authorities
    // =========================================================================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(
                new SimpleGrantedAuthority(
                        "ROLE_" + user.getRole().name()
                )
        );
    }

    // =========================================================================
    // Credentials
    // =========================================================================

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // =========================================================================
    // Account Status
    // =========================================================================

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(user.getEnabled());
    }

}