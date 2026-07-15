package com.erp.school.fee.repository;

import com.erp.school.fee.entity.FeeStructure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FeeStructureRepository extends JpaRepository<FeeStructure, Long> {
    Optional<FeeStructure> findByFeeCode(String feeCode);
    List<FeeStructure> findByClassId(Long classId);
}