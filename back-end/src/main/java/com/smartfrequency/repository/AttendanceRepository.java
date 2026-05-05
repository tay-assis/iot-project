package com.smartfrequency.repository;

import com.smartfrequency.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    boolean existsBySessionIdAndStudentId(Long id, Long id1);
    List<Attendance> findBySessionId(Long sessionId);
}
