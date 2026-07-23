package com.erp.school.exam.controller;

import com.erp.school.common.dto.ApiResponse;
import com.erp.school.exam.dto.ExamRequest;
import com.erp.school.exam.dto.StudentMarkRequest;
import com.erp.school.exam.entity.Exam;
import com.erp.school.exam.entity.Result;
import com.erp.school.exam.entity.StudentMark;
import com.erp.school.exam.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Exam>> createExam(
            @Valid @RequestBody ExamRequest request) {
        Exam saved = examService.createExam(request);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Exam created: " + saved.getExamName(), saved));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Exam>>> getAllExams() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", examService.getAllExams()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Exam>> getExamById(@PathVariable Long id) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", examService.getExamById(id)));
    }

    @GetMapping("/year/{academicYear}")
    public ResponseEntity<ApiResponse<List<Exam>>> getByYear(
            @PathVariable String academicYear) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", examService.getExamsByYear(academicYear)));
    }

    @PostMapping("/marks")
    public ResponseEntity<ApiResponse<StudentMark>> addMark(
            @Valid @RequestBody StudentMarkRequest request) {
        StudentMark saved = examService.addMark(request);
        return ResponseEntity.ok(
            new ApiResponse<>(true,
                "Mark added: " + saved.getMarksObtained() +
                " for student " + saved.getStudentId(), saved));
    }

    @GetMapping("/marks/student/{studentId}")
    public ResponseEntity<ApiResponse<List<StudentMark>>> getMarksByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", examService.getMarksByStudent(studentId)));
    }

    @GetMapping("/marks/exam/{examId}")
    public ResponseEntity<ApiResponse<List<StudentMark>>> getMarksByExam(
            @PathVariable Long examId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", examService.getMarksByExam(examId)));
    }

    @PostMapping("/results/generate/{studentId}/{examId}")
    public ResponseEntity<ApiResponse<Result>> generateResult(
            @PathVariable Long studentId,
            @PathVariable Long examId) {
        Result result = examService.generateResult(studentId, examId);
        return ResponseEntity.ok(
            new ApiResponse<>(true,
                "Result generated. Grade: " + result.getGrade() +
                " | Percentage: " + result.getPercentage() + "%", result));
    }

    @GetMapping("/results/student/{studentId}")
    public ResponseEntity<ApiResponse<List<Result>>> getAllResultsByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", examService.getResultsByStudent(studentId)));
    }

    @GetMapping("/results/student/{studentId}/latest")
    public ResponseEntity<ApiResponse<Result>> getLatestResultByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", examService.getResultByStudent(studentId)));
    }

    @GetMapping("/results")
    public ResponseEntity<ApiResponse<List<Result>>> getAllResults() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", examService.getAllResults()));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Exam Service is running on port 8086");
    }
}