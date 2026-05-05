package com.smartfrequency.controller;

import com.smartfrequency.dto.ClassResponseDTO;
import com.smartfrequency.model.ClassEntity;

import java.util.ArrayList;
import java.util.List;

import com.smartfrequency.model.Professor;
import com.smartfrequency.repository.ClassRepository;
import com.smartfrequency.repository.ProfessorRepository;
import com.smartfrequency.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/classes")
public class ClassController {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    ProfessorRepository professorRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<ClassEntity> create(@RequestBody ClassEntity classEntity) {
        return ResponseEntity.ok(classRepository.save(classEntity));
    }

    @GetMapping
    public ResponseEntity<List<ClassEntity>> all() {

        return ResponseEntity.ok(classRepository.findAll());
    }
    @GetMapping("/professor/classes")
    public ResponseEntity<List<ClassResponseDTO>> getByProfessor(@RequestHeader("Authorization") String authorization) {

        String email = tokenService.validateToken(authorization);


        Professor professor = professorRepository.findByEmail(email);
       List<ClassEntity> classes = classRepository.findByProfessorId(professor.getId());
        List<ClassResponseDTO> response = classes.stream()
                .map(c -> new ClassResponseDTO(c.getId(), c.getName()))
                .toList();

        System.out.println(response.get(0).name());
        return ResponseEntity.ok(response);
    }
}
