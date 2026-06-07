package com.erp.school.exam.repository;

import com.erp.school.exam.entity.StudentMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentMarkRepository extends JpaRepository<StudentMark, Long> {

    List<StudentMark> findByStudentId(Long studentId);

    List<StudentMark> findByStudentIdAndExamId(Long studentId, Long examId);

    List<StudentMark> findByExamId(Long examId);
}