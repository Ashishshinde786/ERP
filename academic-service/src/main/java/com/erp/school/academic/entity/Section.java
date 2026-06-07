package com.erp.school.academic.entity;
 
import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;
 
@Entity
@Table(name = "section")
public class Section extends BaseEntity {
 
    private String sectionName;
 
    @ManyToOne
    @JoinColumn(name = "class_id")
    private AcademicClass academicClass;
 
    public String getSectionName() { return sectionName; }
    public void setSectionName(String sectionName) { this.sectionName = sectionName; }
 
    public AcademicClass getAcademicClass() { return academicClass; }
    public void setAcademicClass(AcademicClass academicClass) { this.academicClass = academicClass; }
}