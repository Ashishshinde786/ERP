package com.erp.school.master.service;

import com.erp.school.master.dto.MasterDataRequest;
import com.erp.school.master.dto.MasterTypeRequest;
import com.erp.school.master.entity.MasterData;
import com.erp.school.master.entity.MasterType;
import com.erp.school.master.repository.MasterDataRepository;
import com.erp.school.master.repository.MasterTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MasterService {

    private final MasterTypeRepository masterTypeRepository;
    private final MasterDataRepository masterDataRepository;

    public MasterService(MasterTypeRepository masterTypeRepository,
                         MasterDataRepository masterDataRepository) {
        this.masterTypeRepository = masterTypeRepository;
        this.masterDataRepository = masterDataRepository;
    }

    @Transactional
    public MasterType createMasterType(MasterTypeRequest request) {
        if (masterTypeRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("Master type code already exists: " + request.getCode());
        }
        MasterType type = new MasterType();
        type.setCode(request.getCode().toUpperCase());
        type.setName(request.getName());
        return masterTypeRepository.save(type);
    }

    public List<MasterType> getAllMasterTypes() {
        return masterTypeRepository.findAll();
    }

    public MasterType getMasterTypeById(Long id) {
        return masterTypeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("MasterType not found: " + id));
    }

    @Transactional
    public MasterData createMasterData(MasterDataRequest request) {
        MasterType type = masterTypeRepository.findById(request.getMasterTypeId())
            .orElseThrow(() -> new RuntimeException(
                "MasterType not found: " + request.getMasterTypeId()));
        MasterData data = new MasterData();
        data.setCode(request.getCode().toUpperCase());
        data.setValue(request.getValue());
        data.setMasterType(type);
        return masterDataRepository.save(data);
    }

    public List<MasterData> getByType(Long typeId) {
        return masterDataRepository.findByMasterType_Id(typeId);
    }

    public List<MasterData> getByTypeCode(String code) {
        return masterDataRepository.findByMasterType_Code(code);
    }
}