/**
 * ============================================================================
 * File: Incident.java
 * Path: src/main/java/com/rakshak/entity/Incident.java
 * ----------------------------------------------------------------------------
 * Description:
 * Entity representing an emergency incident reported by a user.
 *
 * Responsibilities:
 * • Store incident details
 * • Store AI analysis
 * • Track incident status
 * • Link incident with reporting user
 * ============================================================================
 */

package com.rakshak.entity;

// Imports

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.rakshak.enums.IncidentSeverity;
import com.rakshak.enums.IncidentStatus;

// ============================================================================
// Incident Entity
// ============================================================================

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "incidents")
public class Incident extends BaseEntity {

    // =========================================================================
    // Reporter
    // =========================================================================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_by", nullable = false)
    private User reportedBy;

    // =========================================================================
    // Incident Details
    // =========================================================================

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String imageUrl;

    // =========================================================================
    // Location
    // =========================================================================

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;
    // =========================================================================
    // AI Analysis
    // =========================================================================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentSeverity severity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IncidentStatus status;

    @Column(columnDefinition = "TEXT")
    private String aiAnalysis;

    @Column(nullable = false)
    private Integer estimatedVolunteers;

    @Column(nullable = false)
    private Boolean medicalRequired;

    // =========================================================================
    // Resolution Details
    // =========================================================================

    @Column(nullable = false)
    @Builder.Default
    private Boolean resolved = false;

    private java.time.LocalDateTime resolvedAt;

    // =========================================================================
    // Helper Methods
    // =========================================================================

    public boolean isResolved() {
        return Boolean.TRUE.equals(resolved);
    }

    public boolean requiresMedicalHelp() {
        return Boolean.TRUE.equals(medicalRequired);
    }

    public void markResolved() {
        this.resolved = true;
        this.resolvedAt = java.time.LocalDateTime.now();
        this.status = IncidentStatus.RESOLVED;
    }

}