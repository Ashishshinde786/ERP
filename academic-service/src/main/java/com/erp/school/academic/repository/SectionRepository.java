package com.erp.school.academic.repository;

import com.erp.school.academic.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findByAcademicClassId(Long classId);
}