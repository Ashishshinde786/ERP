package com.erp.school.admission.repository;

import com.erp.school.admission.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository
        extends JpaRepository<Student, Long> {

    Optional<Student> findByAdmissionNo(String admissionNo);
    List<Student> findByClassId(Long classId);
    List<Student> findByAcademicYear(String academicYear);
}