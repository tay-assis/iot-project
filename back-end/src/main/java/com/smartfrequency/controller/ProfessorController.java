package com.smartfrequency.controller;

import com.smartfrequency.dto.ProfessorRegisterRequestDTO;
import com.smartfrequency.model.Professor;
import com.smartfrequency.repository.ProfessorRepository;
import com.smartfrequency.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    ProfessorRepository professorRepository;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Valid ProfessorRegisterRequestDTO professorRegisterRequestDTO){
        if(professorRepository.findByEmail(professorRegisterRequestDTO.email()) != null){
            return ResponseEntity.badRequest().build();
        }
        Professor professor = new Professor(professorRegisterRequestDTO);
        professorRepository.save(professor);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Professor>> getAll() {
        return ResponseEntity.ok(professorRepository.findAll());
    }
}
