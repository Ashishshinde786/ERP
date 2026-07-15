package com.erp.school.admission.repository;

import com.erp.school.admission.entity.AdmissionApplication;
import com.erp.school.admission.enums.AdmissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdmissionRepository
        extends JpaRepository<AdmissionApplication, Long> {

    List<AdmissionApplication> findByStatus(AdmissionStatus status);
    List<AdmissionApplication> findByAcademicYear(String academicYear);
    long countByStatus(AdmissionStatus status);
}