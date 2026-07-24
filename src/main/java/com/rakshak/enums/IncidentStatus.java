/**
 * ============================================================================
 * File: IncidentStatus.java
 * Path: src/main/java/com/rakshak/enums/IncidentStatus.java
 * ----------------------------------------------------------------------------
 * Description:
 * Represents the current lifecycle status of an incident.
 *
 * Responsibilities:
 * • Track incident progress
 * ============================================================================
 */

package com.rakshak.enums;

// ============================================================================
// Incident Status
// ============================================================================

public enum IncidentStatus {

    REPORTED,

    ANALYZING,

    VOLUNTEERS_NOTIFIED,

    HELP_IN_PROGRESS,

    RESOLVED,

    CANCELLED

}