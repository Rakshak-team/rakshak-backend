/**
 * ============================================================================
 * File: UserRepository.java
 * Path: src/main/java/com/rakshak/repository/UserRepository.java
 * ----------------------------------------------------------------------------
 * Description:
 * Repository interface for User entity.
 *
 * Responsibilities:
 * • Perform database operations on User entity
 * • Find users by email
 * • Find users by phone number
 * • Check duplicate email and phone number
 * • Support authentication module
 * ============================================================================
 */

package com.rakshak.repository;

// Imports

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rakshak.entity.User;

// ============================================================================
// User Repository
// ============================================================================

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // =========================================================================
    // Authentication Queries
    // =========================================================================

    /**
     * Find user by email.
     *
     * @param email User email
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);

    /**
     * Find user by phone number.
     *
     * @param phoneNumber User phone number
     * @return Optional<User>
     */
    Optional<User> findByPhoneNumber(String phoneNumber);

    /**
     * Check whether email already exists.
     *
     * @param email User email
     * @return true if exists
     */
    boolean existsByEmail(String email);

    /**
     * Check whether phone number already exists.
     *
     * @param phoneNumber User phone number
     * @return true if exists
     */
    boolean existsByPhoneNumber(String phoneNumber);

}