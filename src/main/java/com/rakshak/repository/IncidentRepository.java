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
 * ============================================================================
 */

package com.rakshak.repository;

// Imports

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakshak.entity.Incident;
import com.rakshak.entity.User;
import com.rakshak.enums.IncidentSeverity;
import com.rakshak.enums.IncidentStatus;

// ============================================================================
// Incident Repository
// ============================================================================

public interface IncidentRepository extends JpaRepository<Incident, Long> {

    // =========================================================================
    // Custom Query Methods
    // =========================================================================

    List<Incident> findByReportedBy(User user);

    List<Incident> findByStatus(IncidentStatus status);

    List<Incident> findBySeverity(IncidentSeverity severity);

}