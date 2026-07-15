package com.erp.school.academic.dto;

import jakarta.validation.constraints.NotBlank;

public class AcademicYearRequest {

    @NotBlank(message = "Academic year name is required")
    private String name;

    private Boolean isCurrent;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Boolean getIsCurrent() { return isCurrent; }
    public void setIsCurrent(Boolean isCurrent) { this.isCurrent = isCurrent; }
}