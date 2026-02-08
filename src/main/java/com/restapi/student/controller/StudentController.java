package com.restapi.student.controller;

import com.restapi.student.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST Controller for Student Registration and Management
 * Handles all student-related API endpoints
 */
@RestController
@RequestMapping("/api/students")
public class StudentController {

    // In-memory list to store students
    private List<Student> students = new ArrayList<>();

    // Constructor - Initialize with 5 sample students
    public StudentController() {
        students.add(new Student(1L, "manzi", "delphin", "john.doe@email.com", "Computer Science", 3.8));
        students.add(new Student(2L, "Ngabo", "justin", "jane.smith@email.com", "Computer Science", 3.9));
        students.add(new Student(3L, "Mugisha", "ghslaine", "mike.j@email.com", "Electrical Engineering", 3.5));
        students.add(new Student(4L, "Murerwa", "faith", "sarah.w@email.com", "Business Administration", 3.2));
        students.add(new Student(5L, "Cyiza", "eric", "david.b@email.com", "Computer Science", 3.6));
    }

    /**
     * GET /api/students - Get all students
     * Returns: List of all students with HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    /**
     * GET /api/students/{studentId} - Get student by ID
     * @param studentId - Student ID from URL path
     * Returns: Student object if found (200), or 404 if not found
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET /api/students/major/{major} - Get all students by major
     * @param major - Major name from URL path
     * Returns: List of students with matching major
     */
    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> result = new ArrayList<>();
        
        for (Student student : students) {
            if (student.getMajor().equalsIgnoreCase(major)) {
                result.add(student);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET /api/students/filter?gpa={minGpa} - Filter students by minimum GPA
     * @param gpa - Minimum GPA value (query parameter)
     * Returns: List of students with GPA >= minimum GPA
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterStudentsByGpa(@RequestParam Double gpa) {
        List<Student> result = new ArrayList<>();
        
        for (Student student : students) {
            if (student.getGpa() >= gpa) {
                result.add(student);
            }
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * POST /api/students - Register a new student
     * @param student - Student object from request body
     * Returns: Created student with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        // Generate new ID
        Long maxId = 0L;
        for (Student s : students) {
            if (s.getStudentId() > maxId) {
                maxId = s.getStudentId();
            }
        }
        student.setStudentId(maxId + 1);
        
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    /**
     * PUT /api/students/{studentId} - Update student information
     * @param studentId - Student ID to update
     * @param updatedStudent - Updated student data from request body
     * Returns: Updated student (200) or 404 if not found
     */
    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                updatedStudent.setStudentId(studentId);
                students.set(i, updatedStudent);
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
