package com.erp.school.academic.service;

import com.erp.school.academic.dto.*;
import com.erp.school.academic.entity.*;
import com.erp.school.academic.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicService {

    private final CourseRepository courseRepository;
    private final AcademicClassRepository classRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;
    private final AcademicYearRepository academicYearRepository;

    public AcademicService(
            CourseRepository courseRepository,
            AcademicClassRepository classRepository,
            SectionRepository sectionRepository,
            SubjectRepository subjectRepository,
            AcademicYearRepository academicYearRepository) {
        this.courseRepository = courseRepository;
        this.classRepository = classRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.academicYearRepository = academicYearRepository;
    }

    // ======================== ACADEMIC YEAR ========================

    public AcademicYear createAcademicYear(AcademicYearRequest request) {
        AcademicYear year = new AcademicYear();
        year.setName(request.getName());
        year.setIsCurrent(request.getIsCurrent() != null ? request.getIsCurrent() : false);
        return academicYearRepository.save(year);
    }

    public List<AcademicYear> getAllAcademicYears() {
        return academicYearRepository.findAll();
    }

    // ======================== COURSE ========================

    public Course createCourse(CourseRequest request) {
        if (courseRepository.existsByCourseCode(request.getCourseCode())) {
            throw new RuntimeException("Course code already exists: " + request.getCourseCode());
        }
        Course course = new Course();
        course.setCourseCode(request.getCourseCode());
        course.setCourseName(request.getCourseName());
        course.setCourseType(request.getCourseType());
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found: " + id));
    }

    // ======================== CLASS ========================

    public AcademicClass createClass(ClassRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
            .orElseThrow(() -> new RuntimeException("Course not found: " + request.getCourseId()));
        AcademicClass academicClass = new AcademicClass();
        academicClass.setClassName(request.getClassName());
        academicClass.setCourse(course);
        return classRepository.save(academicClass);
    }

    public List<AcademicClass> getAllClasses() {
        return classRepository.findAll();
    }

    public List<AcademicClass> getClassesByCourse(Long courseId) {
        return classRepository.findByCourseId(courseId);
    }

    // ======================== SECTION ========================

    public Section createSection(SectionRequest request) {
        AcademicClass academicClass = classRepository.findById(request.getClassId())
            .orElseThrow(() -> new RuntimeException("Class not found: " + request.getClassId()));
        Section section = new Section();
        section.setSectionName(request.getSectionName());
        section.setAcademicClass(academicClass);
        return sectionRepository.save(section);
    }

    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    public List<Section> getSectionsByClass(Long classId) {
        return sectionRepository.findByAcademicClassId(classId);
    }

    // ======================== SUBJECT ========================

    public Subject createSubject(SubjectRequest request) {
        AcademicClass academicClass = classRepository.findById(request.getClassId())
            .orElseThrow(() -> new RuntimeException("Class not found: " + request.getClassId()));
        Subject subject = new Subject();
        subject.setSubjectCode(request.getSubjectCode());
        subject.setSubjectName(request.getSubjectName());
        subject.setAcademicClass(academicClass);
        return subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectsByClass(Long classId) {
        return subjectRepository.findByAcademicClassId(classId);
    }
}