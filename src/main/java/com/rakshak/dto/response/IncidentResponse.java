/**
 * ============================================================================
 * File: IncidentResponse.java
 * Path: src/main/java/com/rakshak/dto/response/IncidentResponse.java
 * ----------------------------------------------------------------------------
 * Description:
 * Response DTO returned after reporting or fetching an incident.
 *
 * Responsibilities:
 * • Return incident information
 * • Return AI prediction details
 * ============================================================================
 */

package com.rakshak.dto.response;

// Imports

import com.rakshak.enums.IncidentSeverity;
import com.rakshak.enums.IncidentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// Incident Response DTO
// ============================================================================

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentResponse {

    // =========================================================================
    // Incident Details
    // =========================================================================

    private Long incidentId;

    private String description;

    private String imageUrl;

    // =========================================================================
    // Location
    // =========================================================================

    private Double latitude;

    private Double longitude;

    // =========================================================================
    // AI Analysis
    // =========================================================================

    private IncidentSeverity severity;

    private IncidentStatus status;

    private String aiAnalysis;

    private Integer estimatedVolunteers;

    private Boolean medicalRequired;

    // =========================================================================
    // Response Message
    // =========================================================================

    private String message;

}