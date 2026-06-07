package com.erp.school.gateway.fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback")
    public Map<String, Object> fallback() {
        return Map.of(
                "success", false,
                "message", "Service unavailable. Please try again later."
        );
    }
}