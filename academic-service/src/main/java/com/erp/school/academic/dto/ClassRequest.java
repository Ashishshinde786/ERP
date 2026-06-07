package com.erp.school.academic.dto;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
public class ClassRequest {
 
    // ✅ FILLED: was completely empty before
    @NotBlank(message = "Class name is required")
    private String className;
 
    @NotNull(message = "Course ID is required")
    private Long courseId;      // Which course this class belongs to
 
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
 
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
}