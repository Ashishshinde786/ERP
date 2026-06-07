package com.erp.school.fee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class FeeStructureRequest {

    @NotBlank(message = "Fee code is required")
    private String feeCode;

    @NotBlank(message = "Fee name is required")
    private String feeName;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    private String feeType;   // TUITION, EXAM, LIBRARY, SPORT etc.
    private Long classId;     // null = applies to all classes

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