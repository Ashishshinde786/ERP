package com.erp.school.fee.service;

import com.erp.school.fee.dto.AssignFeeRequest;
import com.erp.school.fee.dto.FeeStructureRequest;
import com.erp.school.fee.dto.PaymentRequest;
import com.erp.school.fee.entity.FeeStructure;
import com.erp.school.fee.entity.Payment;
import com.erp.school.fee.entity.StudentFee;
import com.erp.school.fee.repository.FeeStructureRepository;
import com.erp.school.fee.repository.PaymentRepository;
import com.erp.school.fee.repository.StudentFeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeeService {

    private final FeeStructureRepository feeStructureRepository;
    private final StudentFeeRepository studentFeeRepository;
    private final PaymentRepository paymentRepository;

    public FeeService(
            FeeStructureRepository feeStructureRepository,
            StudentFeeRepository studentFeeRepository,
            PaymentRepository paymentRepository) {
        this.feeStructureRepository = feeStructureRepository;
        this.studentFeeRepository = studentFeeRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public FeeStructure createFeeStructure(FeeStructureRequest request) {
        FeeStructure feeStructure = new FeeStructure();
        feeStructure.setFeeCode(request.getFeeCode());
        feeStructure.setFeeName(request.getFeeName());
        feeStructure.setAmount(request.getAmount());
        feeStructure.setFeeType(request.getFeeType());
        feeStructure.setClassId(request.getClassId());
        return feeStructureRepository.save(feeStructure);
    }

    public List<FeeStructure> getAllFeeStructures() {
        return feeStructureRepository.findAll();
    }

    public FeeStructure getFeeStructureById(Long id) {
        return feeStructureRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fee structure not found: " + id));
    }

    @Transactional
    public StudentFee assignFee(AssignFeeRequest request) {
        FeeStructure feeStructure = feeStructureRepository
            .findById(request.getFeeStructureId())
            .orElseThrow(() -> new RuntimeException(
                "Fee Structure not found: " + request.getFeeStructureId()));

        boolean alreadyAssigned = studentFeeRepository
            .findByStudentIdAndAcademicYear(request.getStudentId(), request.getAcademicYear())
            .stream()
            .anyMatch(sf -> sf.getFeeStructure() != null
                && sf.getFeeStructure().getId().equals(feeStructure.getId()));

        if (alreadyAssigned) {
            throw new RuntimeException(
                "Fee '" + feeStructure.getFeeName() + "' is already assigned to student "
                + request.getStudentId() + " for year " + request.getAcademicYear());
        }

        StudentFee studentFee = new StudentFee();
        studentFee.setStudentId(request.getStudentId());
        studentFee.setFeeStructure(feeStructure);
        studentFee.setTotalAmount(feeStructure.getAmount());
        studentFee.setPaidAmount(0.0);
        studentFee.setDueAmount(feeStructure.getAmount());
        studentFee.setStatus("UNPAID");
        studentFee.setAcademicYear(request.getAcademicYear());

        return studentFeeRepository.save(studentFee);
    }

    @Transactional
    public Payment payFee(PaymentRequest request) {
        StudentFee studentFee = studentFeeRepository
            .findById(request.getStudentFeeId())
            .orElseThrow(() -> new RuntimeException(
                "Student Fee not found: " + request.getStudentFeeId()));

        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new RuntimeException("Payment amount must be greater than zero");
        }

        if (studentFee.getDueAmount() <= 0) {
            throw new RuntimeException("This fee is already fully paid");
        }

        if (request.getAmount() > studentFee.getDueAmount()) {
            throw new RuntimeException(
                "Amount \u20b9" + request.getAmount() +
                " exceeds due amount \u20b9" + studentFee.getDueAmount());
        }

        double newPaid = studentFee.getPaidAmount() + request.getAmount();
        double newDue  = studentFee.getTotalAmount() - newPaid;

        studentFee.setPaidAmount(newPaid);
        studentFee.setDueAmount(newDue);

        if (newDue <= 0) {
            studentFee.setStatus("PAID");
        } else if (newPaid > 0) {
            studentFee.setStatus("PARTIAL");
        }

        studentFeeRepository.save(studentFee);

        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStudentFee(studentFee);
        payment.setReceiptNo("RCP-" + System.currentTimeMillis());

        return paymentRepository.save(payment);
    }

    public List<StudentFee> getStudentFees(Long studentId) {
        return studentFeeRepository.findByStudentId(studentId);
    }

    public List<StudentFee> getStudentFeesByYear(Long studentId, String academicYear) {
        return studentFeeRepository.findByStudentIdAndAcademicYear(studentId, academicYear);
    }

    public List<StudentFee> getUnpaidFees(Long studentId) {
        return studentFeeRepository.findByStudentIdAndStatus(studentId, "UNPAID");
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getStudentPayments(Long studentId) {
        return paymentRepository.findByStudentFeeStudentId(studentId);
    }

    public long countUnpaidFees() {
        return studentFeeRepository.countByStatus("UNPAID");
    }
}