package com.erp.school.exam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ExamRequest {

    @NotBlank(message = "Exam code is required")
    private String examCode;

    @NotBlank(message = "Exam name is required")
    private String examName;

    private String examType;       // UNIT_TEST, HALF_YEARLY, ANNUAL
    private String academicYear;

    @Positive(message = "Total marks must be positive")
    private Double totalMarks;

    @Positive(message = "Passing marks must be positive")
    private Double passingMarks;

    public String getExamCode() { return examCode; }
    public void setExamCode(String examCode) { this.examCode = examCode; }

    public String getExamName() { return examName; }
    public void setExamName(String examName) { this.examName = examName; }

    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double totalMarks) { this.totalMarks = totalMarks; }

    public Double getPassingMarks() { return passingMarks; }
    public void setPassingMarks(Double passingMarks) { this.passingMarks = passingMarks; }
}