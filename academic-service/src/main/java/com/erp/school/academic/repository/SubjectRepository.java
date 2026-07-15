package com.erp.school.academic.repository;

import com.erp.school.academic.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByAcademicClassId(Long classId);
}