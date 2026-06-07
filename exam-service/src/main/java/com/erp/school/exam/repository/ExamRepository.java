package com.erp.school.exam.repository;

import com.erp.school.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    Optional<Exam> findByExamCode(String examCode);

    List<Exam> findByAcademicYear(String academicYear);
}