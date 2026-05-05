package com.smartfrequency.controller;

import com.smartfrequency.model.Enrollment;
import com.smartfrequency.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentRepository.save(enrollment));
    }
}
