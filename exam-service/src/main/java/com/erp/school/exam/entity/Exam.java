package com.erp.school.exam.entity;

import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "exam")
public class Exam extends BaseEntity {

    private String examCode;
    private String examName;
    private String examType;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "total_marks")
    private Double totalMarks;

    @Column(name = "passing_marks")
    private Double passingMarks;

    public String getExamCode() { return examCode; }
    public void setExamCode(String examCode) { this.examCode = examCode; }

    public String getExamName() { return examName; }
    public void setExamName(String examName) { this.examName = examName; }

    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }

    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }

    public Double getTotalMarks() { return totalMarks; }
    public void setTotalMarks(Double totalMarks) { this.totalMarks = totalMarks; }

    public Double getPassingMarks() { return passingMarks; }
    public void setPassingMarks(Double passingMarks) { this.passingMarks = passingMarks; }
}