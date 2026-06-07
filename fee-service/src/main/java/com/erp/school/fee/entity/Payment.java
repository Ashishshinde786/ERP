package com.erp.school.fee.entity;

import com.erp.school.common.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {

    private Double amount;
    private String paymentMode;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "receipt_no")
    private String receiptNo;

    @ManyToOne
    @JoinColumn(name = "student_fee_id")
    private StudentFee studentFee;

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public String getReceiptNo() { return receiptNo; }
    public void setReceiptNo(String receiptNo) { this.receiptNo = receiptNo; }

    public StudentFee getStudentFee() { return studentFee; }
    public void setStudentFee(StudentFee studentFee) { this.studentFee = studentFee; }
}