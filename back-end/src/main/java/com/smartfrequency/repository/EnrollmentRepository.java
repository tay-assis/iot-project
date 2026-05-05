package com.smartfrequency.repository;

import com.smartfrequency.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByStudent_IdAndClazz_Id(Long studentId, Long classId);
    List<Enrollment> findByClazz_Id(Long classId);
}
