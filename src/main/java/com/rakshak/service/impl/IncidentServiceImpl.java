/**
 * ============================================================================
 * File: IncidentServiceImpl.java
 * Path: src/main/java/com/rakshak/service/impl/IncidentServiceImpl.java
 * ----------------------------------------------------------------------------
 * Description:
 * Service implementation responsible for handling incident-related operations.
 *
 * Responsibilities:
 * • Report incidents
 * • Fetch incidents
 * • Retrieve nearby incidents
 * • Update incident status
 * • Resolve incidents
 * ============================================================================
 */

package com.rakshak.service.impl;

// Imports

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rakshak.dto.request.ReportIncidentRequest;
import com.rakshak.dto.response.IncidentResponse;
import com.rakshak.entity.Incident;
import com.rakshak.entity.User;
import com.rakshak.enums.IncidentSeverity;
import com.rakshak.enums.IncidentStatus;
import com.rakshak.exception.ResourceNotFoundException;
import com.rakshak.repository.IncidentRepository;
import com.rakshak.repository.UserRepository;
import com.rakshak.service.IncidentService;
import com.rakshak.util.SecurityUtils;

// ============================================================================
// Incident Service Implementation
// ============================================================================

@Service
public class IncidentServiceImpl implements IncidentService {

    // =========================================================================
    // Dependencies
    // =========================================================================

    private final IncidentRepository incidentRepository;

    private final UserRepository userRepository;

    // =========================================================================
    // Constructor
    // =========================================================================

    public IncidentServiceImpl(
            IncidentRepository incidentRepository,
            UserRepository userRepository) {

        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
    }

    // =========================================================================
    // Report Incident
    // =========================================================================

    @Override
    public IncidentResponse reportIncident(ReportIncidentRequest request) {

        // Get logged-in user's email
        String email = SecurityUtils.getCurrentUserEmail();

        // Find reporter
        User reporter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        // Create incident
        Incident incident = Incident.builder()
                .reportedBy(reporter)
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())

                // Temporary AI values
                .severity(IncidentSeverity.LOW)
                .status(IncidentStatus.REPORTED)
                .aiAnalysis("AI analysis pending.")
                .estimatedVolunteers(1)
                .medicalRequired(false)
                .resolved(false)

                .build();

        // Save incident
        Incident savedIncident = incidentRepository.save(incident);

        // Convert entity to DTO
        IncidentResponse response = mapToResponse(savedIncident);

        response.setMessage("Incident reported successfully.");

        return response;
    }

 // ============================================================================
 // Get Incident By Id
 // ============================================================================

	 @Override
	 public IncidentResponse getIncidentById(Long incidentId) {
	
	     Incident incident = incidentRepository.findById(incidentId)
	             .orElseThrow(() ->
	                     new ResourceNotFoundException(
	                             "Incident not found."));
	
	     return mapToResponse(incident);
	 }

	// ============================================================================
	// Get My Reported Incidents
	// ============================================================================

	@Override
	public List<IncidentResponse> getMyReportedIncidents() {

	    // Get logged-in user's email
	    String email = SecurityUtils.getCurrentUserEmail();

	    // Find logged-in user
	    User user = userRepository.findByEmail(email)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "User not found."));

	    // Fetch incidents reported by current user
	    List<Incident> incidents =
	            incidentRepository.findByReportedBy(user);

	    // Convert entities to response DTOs
	    return incidents.stream()
	            .map(this::mapToResponse)
	            .collect(Collectors.toList());
	}

	// ============================================================================
	// Get Nearby Incidents
	// ============================================================================

	@Override
	public List<IncidentResponse> getNearbyIncidents(
	        Double latitude,
	        Double longitude,
	        Double radiusInKm) {

	    List<Incident> incidents = incidentRepository.findNearbyIncidents(
	            latitude,
	            longitude,
	            radiusInKm);

	    return incidents.stream()
	            .map(this::mapToResponse)
	            .collect(Collectors.toList());
	}

	// ============================================================================
	// Update Incident Status
	// ============================================================================

	@Override
	public IncidentResponse updateIncidentStatus(
	        Long incidentId,
	        IncidentStatus status) {

	    // Find incident
	    Incident incident = incidentRepository.findById(incidentId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Incident not found."));

	    // Update status
	    incident.setStatus(status);

	    // Save updated incident
	    Incident updatedIncident = incidentRepository.save(incident);

	    // Convert entity to response
	    IncidentResponse response = mapToResponse(updatedIncident);

	    response.setMessage("Incident status updated successfully.");

	    return response;
	}

	// ============================================================================
	// Resolve Incident
	// ============================================================================

	@Override
	public IncidentResponse resolveIncident(Long incidentId) {

	    // Find incident
	    Incident incident = incidentRepository.findById(incidentId)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException(
	                            "Incident not found."));

	    // Mark incident as resolved
	    incident.markResolved();

	    // Save updated incident
	    Incident updatedIncident = incidentRepository.save(incident);

	    // Convert entity to response
	    IncidentResponse response = mapToResponse(updatedIncident);

	    response.setMessage("Incident resolved successfully.");

	    return response;
	}

    // =========================================================================
    // Entity → DTO Mapper
    // ============================================================================

    private IncidentResponse mapToResponse(Incident incident) {

        return IncidentResponse.builder()
                .incidentId(incident.getId())
                .description(incident.getDescription())
                .imageUrl(incident.getImageUrl())
                .latitude(incident.getLatitude())
                .longitude(incident.getLongitude())
                .severity(incident.getSeverity())
                .status(incident.getStatus())
                .aiAnalysis(incident.getAiAnalysis())
                .estimatedVolunteers(incident.getEstimatedVolunteers())
                .medicalRequired(incident.getMedicalRequired())
                .build();
    }
}  