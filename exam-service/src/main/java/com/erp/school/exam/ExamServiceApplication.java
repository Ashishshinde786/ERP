package com.erp.school.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.erp.school.exam.repository")
@EntityScan(basePackages = "com.erp.school.exam.entity")
public class ExamServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamServiceApplication.class, args);
    }
}