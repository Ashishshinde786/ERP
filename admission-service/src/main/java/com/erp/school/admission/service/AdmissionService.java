package com.erp.school.admission.service;

import com.erp.school.admission.dto.AdmissionRequest;
import com.erp.school.admission.entity.AdmissionApplication;
import com.erp.school.admission.entity.Student;
import com.erp.school.admission.enums.AdmissionStatus;
import com.erp.school.admission.repository.AdmissionRepository;
import com.erp.school.admission.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdmissionService {

    private final AdmissionRepository admissionRepository;
    private final StudentRepository studentRepository;

    public AdmissionService(
            AdmissionRepository admissionRepository,
            StudentRepository studentRepository) {
        this.admissionRepository = admissionRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public AdmissionApplication apply(AdmissionRequest request) {
        AdmissionApplication application = new AdmissionApplication();
        application.setApplicationNo("APP-" + System.currentTimeMillis());
        application.setStudentName(request.getStudentName());
        application.setEmail(request.getEmail());
        application.setMobile(request.getMobile());
        application.setDateOfBirth(request.getDateOfBirth());
        application.setGender(request.getGender());
        application.setClassId(request.getClassId());
        application.setAcademicYear(request.getAcademicYear());
        application.setStatus(AdmissionStatus.PENDING);
        return admissionRepository.save(application);
    }

    public List<AdmissionApplication> getAll() {
        return admissionRepository.findAll();
    }

    public List<AdmissionApplication> getByStatus(String status) {
        return admissionRepository.findByStatus(AdmissionStatus.valueOf(status.toUpperCase()));
    }

    public List<AdmissionApplication> getByAcademicYear(String academicYear) {
        return admissionRepository.findByAcademicYear(academicYear);
    }

    @Transactional
    public Student approve(Long id) {
        AdmissionApplication application = admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

        if (application.getStatus() == AdmissionStatus.APPROVED) {
            throw new RuntimeException(
                "Application " + application.getApplicationNo() + " is already APPROVED");
        }

        if (application.getStatus() == AdmissionStatus.REJECTED) {
            throw new RuntimeException(
                "Application " + application.getApplicationNo() + " is REJECTED, cannot approve");
        }

        application.setStatus(AdmissionStatus.APPROVED);
        admissionRepository.save(application);

        Student student = new Student();
        student.setAdmissionNo("ADM-" + System.currentTimeMillis());
        student.setStudentName(application.getStudentName());
        student.setEmail(application.getEmail());
        student.setMobile(application.getMobile());
        student.setDateOfBirth(application.getDateOfBirth());
        student.setGender(application.getGender());
        student.setClassId(application.getClassId());
        student.setAcademicYear(application.getAcademicYear());
        student.setApplicationId(application.getId());

        return studentRepository.save(student);
    }

    @Transactional
    public AdmissionApplication reject(Long id) {
        AdmissionApplication application = admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found with id: " + id));

        if (application.getStatus() != AdmissionStatus.PENDING) {
            throw new RuntimeException(
                "Only PENDING applications can be rejected. Current status: "
                + application.getStatus());
        }

        application.setStatus(AdmissionStatus.REJECTED);
        return admissionRepository.save(application);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
    }

    public List<Student> getStudentsByClass(Long classId) {
        return studentRepository.findByClassId(classId);
    }

    public long countPendingApplications() {
        return admissionRepository.countByStatus(AdmissionStatus.PENDING);
    }
}