/**
 * ============================================================================
 * File: ReportIncidentRequest.java
 * Path: src/main/java/com/rakshak/dto/request/ReportIncidentRequest.java
 * ----------------------------------------------------------------------------
 * Description:
 * Request DTO used for reporting a new incident.
 *
 * Responsibilities:
 * • Receive incident details from frontend
 * • Validate request data
 * ============================================================================
 */

package com.rakshak.dto.request;

// Imports

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// Report Incident Request DTO
// ============================================================================

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportIncidentRequest {

    // =========================================================================
    // Incident Details
    // =========================================================================

    @NotBlank(message = "Description is required.")
    private String description;

    @NotBlank(message = "Image URL is required.")
    private String imageUrl;

    // =========================================================================
    // Location
    // =========================================================================

    @NotNull(message = "Latitude is required.")
    @DecimalMin(value = "-90.0", message = "Invalid latitude.")
    @DecimalMax(value = "90.0", message = "Invalid latitude.")
    private Double latitude;

    @NotNull(message = "Longitude is required.")
    @DecimalMin(value = "-180.0", message = "Invalid longitude.")
    @DecimalMax(value = "180.0", message = "Invalid longitude.")
    private Double longitude;

}