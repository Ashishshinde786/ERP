package com.erp.school.admission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.erp.school.admission.repository")
@EntityScan(basePackages = "com.erp.school.admission.entity")
public class AdmissionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdmissionServiceApplication.class, args);
    }
}