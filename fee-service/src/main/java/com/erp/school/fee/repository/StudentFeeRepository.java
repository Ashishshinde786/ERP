package com.erp.school.fee.repository;

import com.erp.school.fee.entity.StudentFee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentFeeRepository extends JpaRepository<StudentFee, Long> {
    List<StudentFee> findByStudentId(Long studentId);
    List<StudentFee> findByStudentIdAndAcademicYear(Long studentId, String academicYear);
    List<StudentFee> findByStudentIdAndStatus(Long studentId, String status);
    long countByStatus(String status);
}