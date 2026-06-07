package com.erp.school.admission.dto;
 
import jakarta.validation.constraints.NotBlank;
 
public class AdmissionRequest {
 
    @NotBlank(message = "Student name is required")
    private String studentName;
 
    private String email;
 
    @NotBlank(message = "Mobile is required")
    private String mobile;
 
    // ✅ ADDED: extra fields
    private String dateOfBirth;
    private String gender;
    private Long classId;
    private String academicYear;
 
    // Getters and Setters
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
 
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
 
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
 
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
 
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
 
    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
 
    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }
}