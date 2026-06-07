package com.erp.school.fee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.erp.school.fee.repository")
@EntityScan(basePackages = "com.erp.school.fee.entity")
public class FeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeeServiceApplication.class, args);
    }
}