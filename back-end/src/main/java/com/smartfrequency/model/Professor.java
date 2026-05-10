package com.smartfrequency.model;

import com.smartfrequency.dto.ProfessorRegisterRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "professors")
public class Professor extends User {

    private String name;


    public Professor(@Valid ProfessorRegisterRequestDTO professorRegisterRequestDTO) {
        this.name = professorRegisterRequestDTO.name();
        this.setEmail(professorRegisterRequestDTO.email());
        this.setPassword(new BCryptPasswordEncoder().encode(professorRegisterRequestDTO.password()));
        this.setRole(Role.PROFESSOR);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Professor() {}
}
