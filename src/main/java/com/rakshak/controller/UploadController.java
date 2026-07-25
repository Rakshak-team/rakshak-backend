/*
 * ============================================================================
 * File: UploadController.java
 * Path: src/main/java/com/rakshak/controller/UploadController.java
 *
 * Description:
 * REST Controller responsible for uploading images to Cloudinary.
 *
 * Responsibilities:
 * - Accept image from client
 * - Call Cloudinary Service
 * - Return uploaded image URL
 * ============================================================================
 */

package com.rakshak.controller;

// Imports
import com.rakshak.dto.response.UploadResponse;
import com.rakshak.service.CloudinaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Controller for image uploads.
 */
@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Image Upload", description = "Upload images to Cloudinary")
public class UploadController {

    private final CloudinaryService cloudinaryService;

    /**
     * Upload image to Cloudinary.
     *
     * @param file Image file
     * @return Uploaded image URL
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload Image")
    public ResponseEntity<UploadResponse> uploadImage(
            @RequestPart("file") MultipartFile file
    ) throws IOException {

        String imageUrl = cloudinaryService.uploadImage(file);

        UploadResponse response = new UploadResponse(
                "Image uploaded successfully",
                imageUrl
        );

        return ResponseEntity.ok(response);
    }
}