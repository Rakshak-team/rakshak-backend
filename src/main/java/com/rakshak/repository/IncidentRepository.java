/**
 * ============================================================================
 * File: IncidentRepository.java
 * Path: src/main/java/com/rakshak/repository/IncidentRepository.java
 * ----------------------------------------------------------------------------
 * Description:
 * Repository interface for performing database operations on Incident entity.
 *
 * Responsibilities:
 * • Store incidents
 * • Retrieve incidents
 * • Filter incidents by reporter, status and severity
 * • Find nearby incidents
 * ============================================================================
 */

package com.rakshak.repository;

// Imports

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rakshak.entity.Incident;
import com.rakshak.entity.User;
import com.rakshak.enums.IncidentSeverity;
import com.rakshak.enums.IncidentStatus;

// ============================================================================
// Incident Repository
// ============================================================================

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    // =========================================================================
    // Find Incidents Reported By User
    // =========================================================================

    List<Incident> findByReportedBy(User user);

    // =========================================================================
    // Find Incidents By Status
    // =========================================================================

    List<Incident> findByStatus(IncidentStatus status);

    // =========================================================================
    // Find Incidents By Severity
    // =========================================================================

    List<Incident> findBySeverity(IncidentSeverity severity);

    // =========================================================================
    // Find Nearby Incidents
    // =========================================================================

    @Query("""
            SELECT i
            FROM Incident i
            WHERE (
                6371 * acos(
                    cos(radians(:latitude))
                    * cos(radians(i.latitude))
                    * cos(radians(i.longitude) - radians(:longitude))
                    + sin(radians(:latitude))
                    * sin(radians(i.latitude))
                )
            ) <= :radius
            """)
    List<Incident> findNearbyIncidents(
            @Param("latitude") Double latitude,
            @Param("longitude") Double longitude,
            @Param("radius") Double radius
    );

}