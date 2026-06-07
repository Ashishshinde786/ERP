package com.erp.school.admission.repository;
 
import com.erp.school.admission.entity.AdmissionApplication;
import com.erp.school.admission.enums.AdmissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.List;
 
public interface AdmissionRepository
        extends JpaRepository<AdmissionApplication, Long> {
 
    // ✅ ADDED: filter by status
    List<AdmissionApplication> findByStatus(AdmissionStatus status);
 
    // ✅ ADDED: filter by academic year
    List<AdmissionApplication> findByAcademicYear(String academicYear);
 
    // ✅ ADDED: count pending
    long countByStatus(AdmissionStatus status);
}