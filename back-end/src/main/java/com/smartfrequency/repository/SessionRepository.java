package com.smartfrequency.repository;

import com.smartfrequency.model.ClassEntity;
import com.smartfrequency.model.Session;
import com.smartfrequency.model.SessionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    Optional<Session> findByStatus(SessionStatus status);
    Optional<Session> findTopByClazzAndStatusOrderByStartTimeDesc(ClassEntity clazz, SessionStatus status);
}
