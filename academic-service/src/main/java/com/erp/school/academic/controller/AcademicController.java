package com.erp.school.academic.controller;

import com.erp.school.academic.dto.*;
import com.erp.school.academic.entity.*;
import com.erp.school.academic.service.AcademicService;
import com.erp.school.common.dto.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academic")
public class AcademicController {

    private final AcademicService academicService;

    public AcademicController(AcademicService academicService) {
        this.academicService = academicService;
    }

    // =================== ACADEMIC YEAR ===================

    @PostMapping("/years")
    public ResponseEntity<ApiResponse<AcademicYear>> createYear(
            @Valid @RequestBody AcademicYearRequest request) {
        AcademicYear saved = academicService.createAcademicYear(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Academic year created", saved));
    }

    @GetMapping("/years")
    public ResponseEntity<ApiResponse<List<AcademicYear>>> getAllYears() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getAllAcademicYears()));
    }

    // =================== COURSE ===================

    @PostMapping("/courses")
    public ResponseEntity<ApiResponse<Course>> createCourse(
            @Valid @RequestBody CourseRequest request) {
        Course saved = academicService.createCourse(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Course created", saved));
    }

    @GetMapping("/courses")
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getAllCourses()));
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getCourseById(id)));
    }

    // =================== CLASS ===================

    @PostMapping("/classes")
    public ResponseEntity<ApiResponse<AcademicClass>> createClass(
            @Valid @RequestBody ClassRequest request) {
        AcademicClass saved = academicService.createClass(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Class created", saved));
    }

    @GetMapping("/classes")
    public ResponseEntity<ApiResponse<List<AcademicClass>>> getAllClasses() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getAllClasses()));
    }

    @GetMapping("/courses/{courseId}/classes")
    public ResponseEntity<ApiResponse<List<AcademicClass>>> getClassesByCourse(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getClassesByCourse(courseId)));
    }

    // =================== SECTION ===================

    @PostMapping("/sections")
    public ResponseEntity<ApiResponse<Section>> createSection(
            @Valid @RequestBody SectionRequest request) {
        Section saved = academicService.createSection(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Section created", saved));
    }

    @GetMapping("/sections")
    public ResponseEntity<ApiResponse<List<Section>>> getAllSections() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getAllSections()));
    }

    @GetMapping("/classes/{classId}/sections")
    public ResponseEntity<ApiResponse<List<Section>>> getSectionsByClass(
            @PathVariable Long classId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getSectionsByClass(classId)));
    }

    // =================== SUBJECT ===================

    @PostMapping("/subjects")
    public ResponseEntity<ApiResponse<Subject>> createSubject(
            @Valid @RequestBody SubjectRequest request) {
        Subject saved = academicService.createSubject(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Subject created", saved));
    }

    @GetMapping("/subjects")
    public ResponseEntity<ApiResponse<List<Subject>>> getAllSubjects() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getAllSubjects()));
    }

    @GetMapping("/classes/{classId}/subjects")
    public ResponseEntity<ApiResponse<List<Subject>>> getSubjectsByClass(
            @PathVariable Long classId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", academicService.getSubjectsByClass(classId)));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Academic Service is running on port 8083");
    }
}