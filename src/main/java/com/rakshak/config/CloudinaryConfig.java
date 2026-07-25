/*
 * ============================================================================
 * File: CloudinaryConfig.java
 * Path: src/main/java/com/rakshak/config/CloudinaryConfig.java
 * Description:
 * Configuration class responsible for creating the Cloudinary Bean.
 *
 * Responsibilities:
 * - Read Cloudinary credentials from application.properties
 * - Create a singleton Cloudinary instance
 * - Make the bean available throughout the application
 * ============================================================================
 */

package com.rakshak.config;

// Imports
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Cloudinary.
 */
@Configuration
public class CloudinaryConfig {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    /**
     * Creates and registers the Cloudinary Bean.
     *
     * @return Configured Cloudinary instance
     */
    @Bean
    public Cloudinary cloudinary() {

        return new Cloudinary(
                ObjectUtils.asMap(
                        "cloud_name", cloudName,
                        "api_key", apiKey,
                        "api_secret", apiSecret,
                        "secure", true
                )
        );
    }
}