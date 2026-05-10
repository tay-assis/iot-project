package com.smartfrequency.controller;

import com.smartfrequency.model.Student;
import com.smartfrequency.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @GetMapping
    public ResponseEntity<List<Student>> all() {

        return ResponseEntity.ok(studentRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student updates) {
        Optional<Student> opt = studentRepository.findById(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();
        Student s = opt.get();
        s.setName(updates.getName());
        s.setRegistrationNumber(updates.getRegistrationNumber());
        s.setFingerprintId(updates.getFingerprintId());
        return ResponseEntity.ok(studentRepository.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) return ResponseEntity.notFound().build();
        studentRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
