package com.smartfrequency.model;

import com.smartfrequency.dto.ClassRegisterRequestDTO;
import com.smartfrequency.repository.ProfessorRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;


@Entity
@Table(name = "classes")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    // getters/setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
}
