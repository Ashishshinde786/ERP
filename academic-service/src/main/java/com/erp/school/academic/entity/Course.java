package com.erp.school.academic.entity;

import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course extends BaseEntity {

    private String courseCode;
    private String courseName;
    private String courseType;

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseType() { return courseType; }
    public void setCourseType(String courseType) { this.courseType = courseType; }
}