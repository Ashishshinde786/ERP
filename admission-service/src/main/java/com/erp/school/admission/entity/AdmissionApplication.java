package com.erp.school.admission.entity;
 
import com.erp.school.admission.enums.AdmissionStatus;
import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;
 
@Entity
@Table(name = "admission_application")
public class AdmissionApplication extends BaseEntity {
 
    @Column(name = "application_no")
    private String applicationNo;
 
    @Column(name = "student_name", nullable = false)
    private String studentName;
 
    private String email;
    private String mobile;
 
    // ✅ ADDED: classId to know which class the student is applying for
    @Column(name = "class_id")
    private Long classId;
 
    // ✅ ADDED: academic year e.g. "2024-25"
    @Column(name = "academic_year")
    private String academicYear;
 
    // ✅ ADDED: date of birth for student verification
    @Column(name = "date_of_birth")
    private String dateOfBirth;
 
    // ✅ ADDED: gender
    private String gender;
 
    @Enumerated(EnumType.STRING)
    private AdmissionStatus status;
 
    // Getters and Setters
    public String getApplicationNo() { return applicationNo; }
    public void setApplicationNo(String applicationNo) { this.applicationNo = applicationNo; }
 
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
 
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
 
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
 
    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
 
    public String getAcademicYear() { return academicYear; }
    public void setAcademicYear(String academicYear) { this.academicYear = academicYear; }
 
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
 
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
 
    public AdmissionStatus getStatus() { return status; }
    public void setStatus(AdmissionStatus status) { this.status = status; }
}