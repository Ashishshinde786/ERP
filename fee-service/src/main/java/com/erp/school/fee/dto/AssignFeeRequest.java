package com.erp.school.fee.dto;

import jakarta.validation.constraints.NotNull;

public class AssignFeeRequest {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Fee structure ID is required")
    private Long feeStructureId;

    private String academicYear;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getFeeStructureId() { return feeStructureId; }
    public void setFeeStructureId(Long feeStructureId) { this.feeStructureId = feeStructureId; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }
}