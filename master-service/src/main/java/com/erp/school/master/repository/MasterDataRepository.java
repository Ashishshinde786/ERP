package com.erp.school.master.repository;

import com.erp.school.master.entity.MasterData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MasterDataRepository extends JpaRepository<MasterData, Long> {

    List<MasterData> findByMasterType_Id(Long masterTypeId);

    List<MasterData> findByMasterType_Code(String code);
}