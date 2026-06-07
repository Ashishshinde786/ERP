package com.erp.school.academic.dto;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
public class SectionRequest {
 
    // ✅ FILLED: was completely empty before
    @NotBlank(message = "Section name is required")
    private String sectionName;  // e.g. "A", "B", "C"
 
    @NotNull(message = "Class ID is required")
    private Long classId;        // Which class this section belongs to
 
    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }
 
    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
}