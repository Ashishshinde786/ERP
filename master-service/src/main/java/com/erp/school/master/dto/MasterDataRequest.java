package com.erp.school.master.dto;
 
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
public class MasterDataRequest {
    @NotBlank(message = "Code is required")
    private String code;
    @NotBlank(message = "Value is required")
    private String value;
    @NotNull(message = "Master type ID is required")
    private Long masterTypeId;
 
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public Long getMasterTypeId() { return masterTypeId; }
    public void setMasterTypeId(Long masterTypeId) { this.masterTypeId = masterTypeId; }
}