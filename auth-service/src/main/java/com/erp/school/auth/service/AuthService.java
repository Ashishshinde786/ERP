package com.erp.school.auth.service;

import com.erp.school.auth.dto.LoginRequest;
import com.erp.school.auth.dto.LoginResponse;
import com.erp.school.auth.dto.RegisterRequest;
import com.erp.school.auth.entity.AppUser;
import com.erp.school.auth.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered: " + request.getEmail());
        }
        if (request.getUsername() != null &&
                userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken: " + request.getUsername());
        }

        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setMobile(request.getMobile());
        user.setInstitutionId(request.getInstitutionId());
        user.setRole(request.getRole() != null ? request.getRole() : "STUDENT");

        userRepository.save(user);
        return "User registered successfully!";
    }

    public LoginResponse login(LoginRequest request) {
        Optional<AppUser> optUser = userRepository.findByEmail(request.getEmail());

        if (optUser.isEmpty()) {
            return new LoginResponse(false, "User not found with email: " + request.getEmail());
        }

        AppUser user = optUser.get();

        if (Boolean.FALSE.equals(user.getIsActive())) {
            return new LoginResponse(false, "Account is deactivated");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse(false, "Invalid password");
        }

        return new LoginResponse(
                true,
                "Login successful",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getInstitutionId()
        );
    }
}