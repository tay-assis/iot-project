package com.smartfrequency.controller;

import com.smartfrequency.dto.EnrollmentRegisterRequestDTO;
import com.smartfrequency.dto.EnrollmentRegisterResponseDTO;
import com.smartfrequency.model.ClassEntity;
import com.smartfrequency.model.Enrollment;
import com.smartfrequency.model.Student;
import com.smartfrequency.repository.ClassRepository;
import com.smartfrequency.repository.EnrollmentRepository;
import com.smartfrequency.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ClassRepository classRepository;

    @PostMapping("/create")
    public ResponseEntity<EnrollmentRegisterResponseDTO> enroll(@RequestBody EnrollmentRegisterRequestDTO enrollmentRegisterRequestDTO) {
        Enrollment enroll = new Enrollment();
        Student student = studentRepository.findStudentById(enrollmentRegisterRequestDTO.studentId());

        System.out.println(student.getName());
        ClassEntity classEntity = classRepository.getReferenceById(enrollmentRegisterRequestDTO.classId());
        System.out.println(classEntity.getName());

        enroll.setStudent(student);
        enroll.setClazz(classEntity);
        enrollmentRepository.save(enroll);

        return ResponseEntity.ok(new EnrollmentRegisterResponseDTO("Successful enrolment"));
    }
}
