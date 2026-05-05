package com.smartfrequency.repository;

import com.smartfrequency.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    List<ClassEntity> findByProfessorId(Long professorId);
}
