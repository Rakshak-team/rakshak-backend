/**
 * ============================================================================
 * File: IncidentService.java
 * Path: src/main/java/com/rakshak/service/IncidentService.java
 * ----------------------------------------------------------------------------
 * Description:
 * Service interface for managing incident-related operations.
 *
 * Responsibilities:
 * • Report new incident
 * • Fetch incident details
 * • Retrieve user's reported incidents
 * • Retrieve nearby incidents
 * • Update incident status
 * • Resolve incident
 * ============================================================================
 */

package com.rakshak.service;

// Imports

import java.util.List;

import com.rakshak.dto.request.ReportIncidentRequest;
import com.rakshak.dto.response.IncidentResponse;
import com.rakshak.enums.IncidentStatus;

// ============================================================================
// Incident Service
// ============================================================================

public interface IncidentService {

    // =========================================================================
    // Report Incident
    // =========================================================================

    IncidentResponse reportIncident(ReportIncidentRequest request);

    // =========================================================================
    // Get Incident
    // =========================================================================

    IncidentResponse getIncidentById(Long incidentId);

    List<IncidentResponse> getMyReportedIncidents();

    List<IncidentResponse> getNearbyIncidents(
            Double latitude,
            Double longitude,
            Double radiusInKm
    );

    // =========================================================================
    // Update Incident
    // =========================================================================

    IncidentResponse updateIncidentStatus(
            Long incidentId,
            IncidentStatus status
    );

    IncidentResponse resolveIncident(Long incidentId);

}