package com.erp.school.master.entity;

import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "master_data")
public class MasterData extends BaseEntity {

    private String code;

    private String value;

    @ManyToOne
    @JoinColumn(name = "master_type_id")
    private MasterType masterType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public MasterType getMasterType() {
        return masterType;
    }

    public void setMasterType(MasterType masterType) {
        this.masterType = masterType;
    }
}