package com.erp.school.auth.controller;

import com.erp.school.auth.dto.LoginRequest;
import com.erp.school.auth.dto.LoginResponse;
import com.erp.school.auth.dto.RegisterRequest;
import com.erp.school.auth.service.AuthService;
import com.erp.school.common.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(
            @Valid @RequestBody RegisterRequest request) {
        String result = authService.register(request);
        return ResponseEntity.ok(new ApiResponse<>(true, result, null));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        if (!response.isSuccess()) {
            return ResponseEntity.status(401).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Auth Service is running on port 8081");
    }
}