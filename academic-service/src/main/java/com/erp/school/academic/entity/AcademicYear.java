package com.erp.school.academic.entity;
 
import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "academic_year")
public class AcademicYear extends BaseEntity {
 
    private String name;         // e.g. "2024-25"
    private Boolean isCurrent;
 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
 
    public Boolean getIsCurrent() { return isCurrent; }
    public void setIsCurrent(Boolean isCurrent) { this.isCurrent = isCurrent; }
}