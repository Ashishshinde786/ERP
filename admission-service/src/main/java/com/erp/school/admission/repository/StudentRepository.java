package com.erp.school.admission.repository;
 
import com.erp.school.admission.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
 
import java.util.List;
import java.util.Optional;
 
public interface StudentRepository
        extends JpaRepository<Student, Long> {
 
    Optional<Student> findByAdmissionNo(String admissionNo);
 
    // ✅ ADDED: get students by class
    List<Student> findByClassId(Long classId);
 
    // ✅ ADDED: get students by academic year
    List<Student> findByAcademicYear(String academicYear);
}