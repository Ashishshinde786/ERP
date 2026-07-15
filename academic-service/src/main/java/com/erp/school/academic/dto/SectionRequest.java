package com.erp.school.academic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SectionRequest {

    @NotBlank(message = "Section name is required")
    private String sectionName;

    @NotNull(message = "Class ID is required")
    private Long classId;

    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }

    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
}