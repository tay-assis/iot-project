package com.smartfrequency.model;

import com.smartfrequency.dto.ProfessorRegisterRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "professors")
public class Professor extends User {

    private String name;


    public Professor(@Valid ProfessorRegisterRequestDTO professorRegisterRequestDTO) {
        this.name = professorRegisterRequestDTO.name();
        this.setEmail(professorRegisterRequestDTO.email());
        this.setPassword(new BCryptPasswordEncoder().encode(professorRegisterRequestDTO.Password()));
        this.setRole(professorRegisterRequestDTO.role());
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Professor() {}
}
