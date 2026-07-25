/**
 * ============================================================================
 * File: UpdateIncidentStatusRequest.java
 * Path: src/main/java/com/rakshak/dto/request/UpdateIncidentStatusRequest.java
 * ----------------------------------------------------------------------------
 * Description:
 * Request DTO for updating incident status.
 * ============================================================================
 */

package com.rakshak.dto.request;

// Imports

import com.rakshak.enums.IncidentStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// ============================================================================
// Update Incident Status Request
// ============================================================================

@Getter
@Setter
@NoArgsConstructor
public class UpdateIncidentStatusRequest {

    @NotNull(message = "Incident status is required.")
    private IncidentStatus status;

}