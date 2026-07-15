package com.erp.school.fee.repository;

import com.erp.school.fee.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByStudentFeeStudentId(Long studentId);
}