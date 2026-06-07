package com.erp.school.admission.entity;
 
import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;
 
@Entity
@Table(name = "student")
public class Student extends BaseEntity {
 
    @Column(name = "admission_no", unique = true)
    private String admissionNo;
 
    @Column(name = "student_name", nullable = false)
    private String studentName;
 
    private String email;
    private String mobile;
 
    // ✅ ADDED: extra student fields
    @Column(name = "date_of_birth")
    private String dateOfBirth;
 
    private String gender;
 
    @Column(name = "class_id")
    private Long classId;
 
    @Column(name = "academic_year")
    private String academicYear;
 
    // ✅ ADDED: link back to original application
    @Column(name = "application_id")
    private Long applicationId;
 
    // Getters and Setters
    public String getAdmissionNo() { return admissionNo; }
    public void setAdmissionNo(String admissionNo) { this.admissionNo = admissionNo; }
 
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
 
    public Long getApplicationId() { return applicationId; }
    public void setApplicationId(Long applicationId) { this.applicationId = applicationId; }
}