package com.erp.school.fee.entity;

import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fee_structure")
public class FeeStructure extends BaseEntity {

    private String feeCode;
    private String feeName;
    private Double amount;
    private String feeType;   // TUITION, EXAM, LIBRARY, SPORT
    private Long classId;     // null = all classes

    public String getFeeCode() { return feeCode; }
    public void setFeeCode(String feeCode) { this.feeCode = feeCode; }

    public String getFeeName() { return feeName; }
    public void setFeeName(String feeName) { this.feeName = feeName; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getFeeType() { return feeType; }
    public void setFeeType(String feeType) { this.feeType = feeType; }

    public Long getClassId() { return classId; }
    public void setClassId(Long classId) { this.classId = classId; }
}