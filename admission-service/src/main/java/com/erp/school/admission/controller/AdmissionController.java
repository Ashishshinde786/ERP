package com.erp.school.admission.controller;

import com.erp.school.admission.dto.AdmissionRequest;
import com.erp.school.admission.entity.AdmissionApplication;
import com.erp.school.admission.entity.Student;
import com.erp.school.admission.service.AdmissionService;
import com.erp.school.common.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admissions")
@CrossOrigin(origins = "*")
public class AdmissionController {

    private final AdmissionService service;

    public AdmissionController(AdmissionService service) {
        this.service = service;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApiResponse<AdmissionApplication>> apply(
            @Valid @RequestBody AdmissionRequest request) {
        AdmissionApplication saved = service.apply(request);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Application submitted: " + saved.getApplicationNo(), saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AdmissionApplication>>> getAll() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", service.getAll()));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<AdmissionApplication>>> getByStatus(
            @PathVariable String status) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", service.getByStatus(status)));
    }

    @GetMapping("/year/{academicYear}")
    public ResponseEntity<ApiResponse<List<AdmissionApplication>>> getByYear(
            @PathVariable String academicYear) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", service.getByAcademicYear(academicYear)));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<ApiResponse<Student>> approve(@PathVariable Long id) {
        Student student = service.approve(id);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Approved. Student ID: " + student.getAdmissionNo(), student));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<ApiResponse<AdmissionApplication>> reject(@PathVariable Long id) {
        AdmissionApplication app = service.reject(id);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Application rejected", app));
    }

    @GetMapping("/students")
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", service.getAllStudents()));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", service.getStudentById(id)));
    }

    @GetMapping("/students/class/{classId}")
    public ResponseEntity<ApiResponse<List<Student>>> getStudentsByClass(
            @PathVariable Long classId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", service.getStudentsByClass(classId)));
    }

    @GetMapping("/stats/pending")
    public ResponseEntity<ApiResponse<Long>> getPendingCount() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", service.countPendingApplications()));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Admission Service is running on port 8084");
    }
}