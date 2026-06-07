package com.erp.school.academic.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Academic Service API")
                .description("Courses, Classes, Sections, Subjects, Academic Years.\n\n" +
                             "Direct: http://localhost:8083/swagger-ui.html\n" +
                             "Via Gateway: http://localhost:8080/swagger-ui.html (select 'Academic Service')")
                .version("1.0.0")
                .contact(new Contact().name("ERP Team").email("admin@school.com")))
            .components(new Components()
                .addSecuritySchemes("bearerAuth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
    }
}