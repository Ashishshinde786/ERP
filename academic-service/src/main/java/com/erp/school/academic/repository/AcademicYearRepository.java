package com.erp.school.academic.repository;
 
import com.erp.school.academic.entity.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
 
// ✅ FIXED: now properly extends JpaRepository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {
    Optional<AcademicYear> findByIsCurrent(Boolean isCurrent);
}