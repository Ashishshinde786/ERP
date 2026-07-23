package com.erp.school.master.controller;

import com.erp.school.common.dto.ApiResponse;
import com.erp.school.master.dto.MasterDataRequest;
import com.erp.school.master.dto.MasterTypeRequest;
import com.erp.school.master.entity.MasterData;
import com.erp.school.master.entity.MasterType;
import com.erp.school.master.service.MasterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/master")
public class MasterController {

    private final MasterService service;

    public MasterController(MasterService service) {
        this.service = service;
    }

    @PostMapping("/master-types")
    public ResponseEntity<ApiResponse<MasterType>> createMasterType(
            @Valid @RequestBody MasterTypeRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true,
            "Master type created", service.createMasterType(request)));
    }

    @GetMapping("/master-types")
    public ResponseEntity<ApiResponse<List<MasterType>>> getMasterTypes() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success",
            service.getAllMasterTypes()));
    }

    @GetMapping("/master-types/{id}")
    public ResponseEntity<ApiResponse<MasterType>> getMasterTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success",
            service.getMasterTypeById(id)));
    }

    @PostMapping("/master-data")
    public ResponseEntity<ApiResponse<MasterData>> createMasterData(
            @Valid @RequestBody MasterDataRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true,
            "Master data created", service.createMasterData(request)));
    }

    @GetMapping("/master-data/type/{id}")
    public ResponseEntity<ApiResponse<List<MasterData>>> getByType(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success",
            service.getByType(id)));
    }

    @GetMapping("/master-data/code/{code}")
    public ResponseEntity<ApiResponse<List<MasterData>>> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success",
            service.getByTypeCode(code)));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Master Service running on port 8082");
    }
}