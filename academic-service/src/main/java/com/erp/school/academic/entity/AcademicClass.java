package com.erp.school.academic.entity;

import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "academic_class")
public class AcademicClass extends BaseEntity {

    private String className;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}