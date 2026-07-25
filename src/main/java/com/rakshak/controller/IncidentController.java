/**
 * ============================================================================
 * File: IncidentController.java
 * Path: src/main/java/com/rakshak/controller/IncidentController.java
 * ----------------------------------------------------------------------------
 * Description:
 * REST Controller for managing incident-related APIs.
 *
 * Responsibilities:
 * • Report new incident
 * • Retrieve incident details
 * • Retrieve user's reported incidents
 * • Retrieve nearby incidents
 * • Update incident status
 * • Resolve incident
 * ============================================================================
 */

package com.rakshak.controller;

// Imports

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rakshak.dto.request.ReportIncidentRequest;
import com.rakshak.dto.request.UpdateIncidentStatusRequest;
import com.rakshak.dto.response.IncidentResponse;
import com.rakshak.service.IncidentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

// ============================================================================
// Incident Controller
// ============================================================================

@RestController
@RequestMapping("/incidents")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(
        name = "Incident APIs",
        description = "APIs for reporting and managing incidents."
)
@SecurityRequirement(name = "Bearer Authentication")
public class IncidentController {

    // =========================================================================
    // Dependencies
    // =========================================================================

    private final IncidentService incidentService;

    // =========================================================================
    // Report Incident API
    // =========================================================================

    @Operation(summary = "Report a new incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incident reported successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping
    public ResponseEntity<IncidentResponse> reportIncident(
            @Valid @RequestBody ReportIncidentRequest request) {

        IncidentResponse response = incidentService.reportIncident(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // =========================================================================
    // Get Incident By ID
    // =========================================================================

    @Operation(summary = "Get incident details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Incident not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getIncident(
            @PathVariable Long id) {

        IncidentResponse response = incidentService.getIncidentById(id);

        return ResponseEntity.ok(response);
    }

    // =========================================================================
    // Get My Reported Incidents
    // =========================================================================

    @Operation(summary = "Get all incidents reported by the logged-in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidents retrieved successfully")
    })
    @GetMapping("/my")
    public ResponseEntity<List<IncidentResponse>> getMyIncidents() {

        List<IncidentResponse> response =
                incidentService.getMyReportedIncidents();

        return ResponseEntity.ok(response);
    }
    // =========================================================================
    // Get Nearby Incidents
    // =========================================================================

    @Operation(summary = "Get nearby incidents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nearby incidents retrieved successfully")
    })
    @GetMapping("/nearby")
    public ResponseEntity<List<IncidentResponse>> getNearbyIncidents(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam(defaultValue = "5.0") Double radiusInKm) {

        List<IncidentResponse> response = incidentService.getNearbyIncidents(
                latitude,
                longitude,
                radiusInKm
        );

        return ResponseEntity.ok(response);
    }

    // =========================================================================
    // Update Incident Status
    // =========================================================================

    @Operation(summary = "Update incident status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident status updated successfully"),
            @ApiResponse(responseCode = "404", description = "Incident not found")
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<IncidentResponse> updateIncidentStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateIncidentStatusRequest request) {

        IncidentResponse response = incidentService.updateIncidentStatus(
                id,
                request.getStatus()
        );

        return ResponseEntity.ok(response);
    }

    // =========================================================================
    // Resolve Incident
    // =========================================================================

    @Operation(summary = "Resolve an incident")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident resolved successfully"),
            @ApiResponse(responseCode = "404", description = "Incident not found")
    })
    @PutMapping("/{id}/resolve")
    public ResponseEntity<IncidentResponse> resolveIncident(
            @PathVariable Long id) {

        IncidentResponse response = incidentService.resolveIncident(id);

        return ResponseEntity.ok(response);
    }

}