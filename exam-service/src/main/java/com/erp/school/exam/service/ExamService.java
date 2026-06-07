package com.erp.school.exam.service;

import com.erp.school.exam.dto.ExamRequest;
import com.erp.school.exam.dto.StudentMarkRequest;
import com.erp.school.exam.entity.Exam;
import com.erp.school.exam.entity.Result;
import com.erp.school.exam.entity.StudentMark;
import com.erp.school.exam.repository.ExamRepository;
import com.erp.school.exam.repository.ResultRepository;
import com.erp.school.exam.repository.StudentMarkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final StudentMarkRepository studentMarkRepository;
    private final ResultRepository resultRepository;

    public ExamService(
            ExamRepository examRepository,
            StudentMarkRepository studentMarkRepository,
            ResultRepository resultRepository) {
        this.examRepository = examRepository;
        this.studentMarkRepository = studentMarkRepository;
        this.resultRepository = resultRepository;
    }

    // ======================== EXAM ========================

    @Transactional
    public Exam createExam(ExamRequest request) {
        if (examRepository.findByExamCode(request.getExamCode()).isPresent()) {
            throw new RuntimeException("Exam code already exists: " + request.getExamCode());
        }
        Exam exam = new Exam();
        exam.setExamCode(request.getExamCode());
        exam.setExamName(request.getExamName());
        exam.setExamType(request.getExamType());
        exam.setAcademicYear(request.getAcademicYear());
        exam.setTotalMarks(request.getTotalMarks());
        exam.setPassingMarks(request.getPassingMarks());
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Exam not found: " + id));
    }

    public List<Exam> getExamsByYear(String academicYear) {
        return examRepository.findByAcademicYear(academicYear);
    }

    // ======================== MARKS ========================

    @Transactional
    public StudentMark addMark(StudentMarkRequest request) {
        Exam exam = examRepository.findById(request.getExamId())
            .orElseThrow(() -> new RuntimeException("Exam not found: " + request.getExamId()));

        if (exam.getTotalMarks() != null &&
                request.getMarksObtained() > exam.getTotalMarks()) {
            throw new RuntimeException(
                "Marks " + request.getMarksObtained() +
                " exceed total marks " + exam.getTotalMarks());
        }

        StudentMark mark = new StudentMark();
        mark.setStudentId(request.getStudentId());
        mark.setSubjectId(request.getSubjectId());
        mark.setSubjectName(request.getSubjectName());
        mark.setMarksObtained(request.getMarksObtained());
        mark.setExam(exam);

        return studentMarkRepository.save(mark);
    }

    public List<StudentMark> getMarksByStudent(Long studentId) {
        return studentMarkRepository.findByStudentId(studentId);
    }

    public List<StudentMark> getMarksByExam(Long examId) {
        return studentMarkRepository.findByExamId(examId);
    }

    // ======================== RESULT ========================

    @Transactional
    public Result generateResult(Long studentId, Long examId) {
        List<StudentMark> marks = studentMarkRepository
            .findByStudentIdAndExamId(studentId, examId);

        if (marks.isEmpty()) {
            throw new RuntimeException(
                "No marks found for student " + studentId + " in exam " + examId);
        }

        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new RuntimeException("Exam not found: " + examId));

        double totalObtained = marks.stream()
            .mapToDouble(StudentMark::getMarksObtained)
            .sum();

        double totalPossible = exam.getTotalMarks() != null
            ? exam.getTotalMarks() * marks.size()
            : marks.size() * 100.0;

        double percentage = (totalObtained / totalPossible) * 100;
        String grade = calculateGrade(percentage);
        boolean isPassed = exam.getPassingMarks() == null ||
            (totalObtained / marks.size()) >= exam.getPassingMarks();

        // Upsert: update existing result or create new one
        Result result = resultRepository
            .findByStudentIdAndExam_Id(studentId, examId)   // FIXED
            .orElse(new Result());

        result.setStudentId(studentId);
        result.setExam(exam);
        result.setTotalMarksObtained(totalObtained);
        result.setPercentage(Math.round(percentage * 100.0) / 100.0);
        result.setGrade(grade);
        result.setIsPassed(isPassed);

        return resultRepository.save(result);
    }

    public Result getResultByStudent(Long studentId) {
        List<Result> results = resultRepository.findByStudentId(studentId);
        if (results.isEmpty()) {
            throw new RuntimeException("Result not found for student: " + studentId);
        }
        return results.get(results.size() - 1);
    }

    public List<Result> getResultsByStudent(Long studentId) {
        List<Result> results = resultRepository.findByStudentId(studentId);
        if (results.isEmpty()) {
            throw new RuntimeException("No results found for student: " + studentId);
        }
        return results;
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    // ======================== PRIVATE HELPERS ========================

    private String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B+";
        if (percentage >= 60) return "B";
        if (percentage >= 50) return "C";
        if (percentage >= 40) return "D";
        if (percentage >= 35) return "E";
        return "FAIL";
    }
}