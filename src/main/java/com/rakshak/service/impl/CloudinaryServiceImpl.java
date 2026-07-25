/*
 * ============================================================================
 * File: CloudinaryServiceImpl.java
 * Path: src/main/java/com/rakshak/service/impl/CloudinaryServiceImpl.java
 *
 * Description:
 * Implementation of CloudinaryService.
 *
 * Responsibilities:
 * - Upload image to Cloudinary
 * - Return uploaded image URL
 * ============================================================================
 */

package com.rakshak.service.impl;

// Imports
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.rakshak.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Cloudinary Service Implementation.
 */
@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {

        Map<?, ?> uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.emptyMap()
        );

        return uploadResult.get("secure_url").toString();
    }
}