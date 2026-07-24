/**
 * ============================================================================
 * File: JpaAuditConfig.java
 * Path: src/main/java/com/rakshak/config/JpaAuditConfig.java
 * ----------------------------------------------------------------------------
 * Description:
 * Enables JPA auditing across the Rakshak backend.
 *
 * Responsibilities:
 * • Enable Spring Data JPA Auditing
 * • Automatically manage audit fields
 * • Support BaseEntity auditing
 * ============================================================================
 */

package com.rakshak.config;

// Imports
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// ============================================================================
// JPA Audit Configuration
// ============================================================================

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {

}