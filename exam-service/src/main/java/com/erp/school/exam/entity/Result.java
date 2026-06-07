package com.erp.school.exam.entity;

import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "result")
public class Result extends BaseEntity {

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    private Double percentage;
    private String grade;

    @Column(name = "total_marks_obtained")
    private Double totalMarksObtained;

    @Column(name = "is_passed")
    private Boolean isPassed;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Double getPercentage() { return percentage; }
    public void setPercentage(Double percentage) { this.percentage = percentage; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public Double getTotalMarksObtained() { return totalMarksObtained; }
    public void setTotalMarksObtained(Double v) { this.totalMarksObtained = v; }

    public Boolean getIsPassed() { return isPassed; }
    public void setIsPassed(Boolean isPassed) { this.isPassed = isPassed; }

    public Exam getExam() { return exam; }
    public void setExam(Exam exam) { this.exam = exam; }
}