package com.erp.school.academic.repository;

import com.erp.school.academic.entity.AcademicClass;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AcademicClassRepository extends JpaRepository<AcademicClass, Long> {
    List<AcademicClass> findByCourseId(Long courseId);
}