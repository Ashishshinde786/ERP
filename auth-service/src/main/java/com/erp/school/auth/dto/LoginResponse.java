package com.erp.school.auth.dto;

public class LoginResponse {

    private boolean success;
    private String message;
    private Long userId;
    private String username;
    private String email;
    private String role;
    private Long institutionId;

    public LoginResponse(boolean success, String message,
                         Long userId, String username,
                         String email, String role,
                         Long institutionId) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.role = role;
        this.institutionId = institutionId;
    }

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public Long getInstitutionId() { return institutionId; }
}