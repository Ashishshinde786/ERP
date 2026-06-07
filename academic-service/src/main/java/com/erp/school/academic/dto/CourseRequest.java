package com.erp.school.academic.dto;
 
import jakarta.validation.constraints.NotBlank;
 
public class CourseRequest {
 
    // ✅ FILLED: was completely empty before
    @NotBlank(message = "Course code is required")
    private String courseCode;
 
    @NotBlank(message = "Course name is required")
    private String courseName;
 
    private String courseType;  // PRIMARY, SECONDARY, UG, PG
 
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
 
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
 
    public String getCourseType() { return courseType; }
    public void setCourseType(String courseType) { this.courseType = courseType; }
}