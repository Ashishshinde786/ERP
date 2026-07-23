package com.erp.school.auth.service;

import com.erp.school.auth.dto.LoginRequest;
import com.erp.school.auth.dto.LoginResponse;
import com.erp.school.auth.dto.RegisterRequest;
import com.erp.school.auth.entity.AppUser;
import com.erp.school.auth.repository.UserRepository;
import com.erp.school.common.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private static final Set<String> SELF_REGISTERABLE_ROLES = Set.of("STUDENT", "TEACHER", "STAFF");
    private static final String DEFAULT_ROLE = "STUDENT";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
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

        String requestedRole = request.getRole() != null ? request.getRole().toUpperCase() : null;
        String role = (requestedRole != null && SELF_REGISTERABLE_ROLES.contains(requestedRole))
                ? requestedRole
                : DEFAULT_ROLE;
        user.setRole(role);

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

        LoginResponse response = new LoginResponse(
                true,
                "Login successful",
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getInstitutionId()
        );

        response.setToken(jwtUtil.generateToken(
                user.getId(), user.getEmail(), user.getRole(), user.getInstitutionId()
        ));

        return response;
    }
}