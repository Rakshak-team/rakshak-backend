/**
 * ============================================================================
 * File: BaseEntity.java
 * Path: src/main/java/com/rakshak/entity/BaseEntity.java
 * ----------------------------------------------------------------------------
 * Description:
 * Base entity for all JPA entities in the Rakshak backend.
 *
 * Responsibilities:
 * • Automatically store entity creation timestamp
 * • Automatically update entity modification timestamp
 * • Provide common audit fields for all database entities
 * ============================================================================
 */

package com.rakshak.entity;

// Imports
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;



// ============================================================================
// Base Entity
// ============================================================================

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
	
	// ========================================================================
	// Primary Key
	// ========================================================================

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    // ========================================================================
    // Audit Fields
    // ========================================================================

    // Automatically stores the timestamp when the record is created
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Automatically updates the timestamp whenever the record is modified
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}