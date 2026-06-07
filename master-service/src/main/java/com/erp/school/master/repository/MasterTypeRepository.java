package com.erp.school.master.repository;

import com.erp.school.master.entity.MasterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasterTypeRepository extends JpaRepository<MasterType, Long> {

    Optional<MasterType> findByCode(String code);

    boolean existsByCode(String code);
}