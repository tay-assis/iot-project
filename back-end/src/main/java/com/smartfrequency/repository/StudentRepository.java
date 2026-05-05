package com.smartfrequency.repository;

import com.smartfrequency.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByFingerprintId(Long fingerprintId);
}
