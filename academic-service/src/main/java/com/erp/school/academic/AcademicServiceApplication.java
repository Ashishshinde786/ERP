package com.erp.school.academic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.erp.school.academic.repository")
@EntityScan(basePackages = "com.erp.school.academic.entity")
public class AcademicServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcademicServiceApplication.class, args);
    }
}