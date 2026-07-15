package com.erp.school.notification.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
            .title("Notification Service API")
            .description("Send and manage school notifications.\n\n" +
                         "Direct: http://localhost:8087/swagger-ui.html")
            .version("1.0.0")
            .contact(new Contact().name("ERP Team").email("admin@school.com")));
    }
}