package com.erp.school.fee.controller;

import com.erp.school.common.dto.ApiResponse;
import com.erp.school.fee.dto.AssignFeeRequest;
import com.erp.school.fee.dto.FeeStructureRequest;
import com.erp.school.fee.dto.PaymentRequest;
import com.erp.school.fee.entity.FeeStructure;
import com.erp.school.fee.entity.Payment;
import com.erp.school.fee.entity.StudentFee;
import com.erp.school.fee.service.FeeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
@CrossOrigin(origins = "*")
public class FeeController {

    private final FeeService feeService;

    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    // =================== FEE STRUCTURE ===================

    @PostMapping("/structures")
    public ResponseEntity<ApiResponse<FeeStructure>> createFeeStructure(
            @Valid @RequestBody FeeStructureRequest request) {
        FeeStructure saved = feeService.createFeeStructure(request);
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Fee structure created: " + saved.getFeeName(), saved));
    }

    @GetMapping("/structures")
    public ResponseEntity<ApiResponse<List<FeeStructure>>> getAllFeeStructures() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", feeService.getAllFeeStructures()));
    }

    @GetMapping("/structures/{id}")
    public ResponseEntity<ApiResponse<FeeStructure>> getFeeStructureById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", feeService.getFeeStructureById(id)));
    }

    // =================== ASSIGN FEE ===================

    @PostMapping("/assign")
    public ResponseEntity<ApiResponse<StudentFee>> assignFee(
            @Valid @RequestBody AssignFeeRequest request) {
        StudentFee saved = feeService.assignFee(request);
        return ResponseEntity.ok(
            new ApiResponse<>(true,
                "Fee assigned to student " + saved.getStudentId() +
                ". Due: \u20b9" + saved.getDueAmount(), saved));
    }

    // =================== PAYMENT ===================

    @PostMapping("/pay")
    public ResponseEntity<ApiResponse<Payment>> payFee(
            @Valid @RequestBody PaymentRequest request) {
        Payment payment = feeService.payFee(request);
        return ResponseEntity.ok(
            new ApiResponse<>(true,
                "Payment received. Receipt: " + payment.getReceiptNo(), payment));
    }

    // =================== QUERIES ===================

    @GetMapping("/student/{studentId}")
    public ResponseEntity<ApiResponse<List<StudentFee>>> getStudentFees(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", feeService.getStudentFees(studentId)));
    }

    @GetMapping("/student/{studentId}/year/{academicYear}")
    public ResponseEntity<ApiResponse<List<StudentFee>>> getStudentFeesByYear(
            @PathVariable Long studentId,
            @PathVariable String academicYear) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success",
                feeService.getStudentFeesByYear(studentId, academicYear)));
    }

    @GetMapping("/student/{studentId}/unpaid")
    public ResponseEntity<ApiResponse<List<StudentFee>>> getUnpaidFees(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", feeService.getUnpaidFees(studentId)));
    }

    @GetMapping("/payments")
    public ResponseEntity<ApiResponse<List<Payment>>> getAllPayments() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", feeService.getAllPayments()));
    }

    @GetMapping("/payments/student/{studentId}")
    public ResponseEntity<ApiResponse<List<Payment>>> getStudentPayments(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", feeService.getStudentPayments(studentId)));
    }

    @GetMapping("/stats/unpaid-count")
    public ResponseEntity<ApiResponse<Long>> getUnpaidCount() {
        return ResponseEntity.ok(
            new ApiResponse<>(true, "Success", feeService.countUnpaidFees()));
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Fee Service is running on port 8085");
    }
}