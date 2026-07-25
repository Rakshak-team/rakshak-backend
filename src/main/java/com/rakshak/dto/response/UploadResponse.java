/*
 * ============================================================================
 * File: UploadResponse.java
 * Path: src/main/java/com/rakshak/dto/response/UploadResponse.java
 *
 * Description:
 * DTO returned after successful image upload.
 *
 * Responsibilities:
 * - Return uploaded image URL
 * - Return success message
 * ============================================================================
 */

package com.rakshak.dto.response;

// Imports
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Upload Response DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponse {

    /**
     * Success message.
     */
    private String message;

    /**
     * Uploaded image URL.
     */
    private String imageUrl;
}