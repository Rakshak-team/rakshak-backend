/**
 * ============================================================================
 * File: OpenApiConfig.java
 * Path: src/main/java/com/rakshak/config/OpenApiConfig.java
 * ----------------------------------------------------------------------------
 * Description:
 * Swagger/OpenAPI configuration.
 *
 * Responsibilities:
 * • Configure OpenAPI metadata
 * • Enable JWT Bearer Authentication in Swagger UI
 * • Display API documentation
 * ============================================================================
 */

package com.rakshak.config;

// Imports

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

// ============================================================================
// OpenAPI Configuration
// ============================================================================

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {

        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()

                .info(new Info()

                        .title("Rakshak Backend API")

                        .version("1.0.0")

                        .description("""
                                Rakshak is an AI-powered emergency response platform.
                                
                                Features:
                                • Authentication
                                • Incident Reporting
                                • Volunteer Management
                                • AI Analysis
                                • Notifications
                                """)

                        .contact(new Contact()

                                .name("Rakshak Team")

                                .email("support@rakshak.com"))

                        .license(new License()

                                .name("MIT License")))

                .addSecurityItem(

                        new SecurityRequirement()

                                .addList(securitySchemeName))

                .components(

                        new Components()

                                .addSecuritySchemes(

                                        securitySchemeName,

                                        new SecurityScheme()

                                                .name(securitySchemeName)

                                                .type(SecurityScheme.Type.HTTP)

                                                .scheme("bearer")

                                                .bearerFormat("JWT")));
    }

}