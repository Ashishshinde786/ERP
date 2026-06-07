package com.erp.school.academic.dto;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
public class SubjectRequest {
 
    // ✅ FILLED: was completely empty before
    @NotBlank(message = "Subject code is required")
    private String subjectCode;  // e.g. "MATH101"
 
    @NotBlank(message = "Subject name is required")
    private String subjectName;  // e.g. "Mathematics"
 
    @NotNull(message = "Class ID is required")
    private Long classId;        // Which class this subject is taught in
 
    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }
 
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
 
    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
}