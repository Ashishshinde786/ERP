package com.erp.school.academic.entity;
 
import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;
 
@Entity
@Table(name = "subject")
public class Subject extends BaseEntity {
 
    private String subjectCode;
    private String subjectName;
 
    @ManyToOne
    @JoinColumn(name = "class_id")
    private AcademicClass academicClass;
 
    public String getSubjectCode() { return subjectCode; }
    public void setSubjectCode(String subjectCode) { this.subjectCode = subjectCode; }
 
    public String getSubjectName() { return subjectName; }
    public void setSubjectName(String subjectName) { this.subjectName = subjectName; }
 
    public AcademicClass getAcademicClass() { return academicClass; }
    public void setAcademicClass(AcademicClass academicClass) { this.academicClass = academicClass; }
}