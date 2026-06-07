package com.erp.school.academic.dto;
 
import jakarta.validation.constraints.NotBlank;
 
public class AcademicYearRequest {
 
    @NotBlank(message = "Academic year name is required")
    private String name;         // e.g. "2024-25"
 
    private Boolean isCurrent;  // Is this the active academic year?
 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
 
    public Boolean getIsCurrent() { return isCurrent; }
    public void setIsCurrent(Boolean isCurrent) { this.isCurrent = isCurrent; }
}