package com.smartfrequency.dto;

import com.smartfrequency.model.Role;

public record ProfessorRegisterRequestDTO(String name, String email, String Password, Role role){
}
