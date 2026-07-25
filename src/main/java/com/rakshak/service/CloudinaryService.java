/*
 * ============================================================================
 * File: CloudinaryService.java
 * Path: src/main/java/com/rakshak/service/CloudinaryService.java
 *
 * Description:
 * Service interface responsible for uploading images to Cloudinary.
 *
 * Responsibilities:
 * - Accept image from controller
 * - Upload image to Cloudinary
 * - Return uploaded image URL
 * ============================================================================
 */

package com.rakshak.service;

// Imports
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Cloudinary Service Interface.
 */
public interface CloudinaryService {

    /**
     * Uploads an image to Cloudinary.
     *
     * @param file Image file
     * @return Public URL of uploaded image
     * @throws IOException if upload fails
     */
    String uploadImage(MultipartFile file) throws IOException;
}