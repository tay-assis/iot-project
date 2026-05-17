package com.smartfrequency.controller;

import com.smartfrequency.model.Session;
import com.smartfrequency.model.SessionStatus;
import com.smartfrequency.model.ClassEntity;
import com.smartfrequency.repository.ClassRepository;
import com.smartfrequency.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ClassRepository classRepository;

    @PostMapping("/start")
    public ResponseEntity<?> start(@RequestBody Long classId) {
        Optional<Session> openSession = sessionRepository.findByStatus(SessionStatus.OPEN);

        if (openSession.isPresent()) {
            System.out.println("já tem uma sessão aberta");
            return ResponseEntity
                    .badRequest()
                    .body("Já existe uma sessão aberta");
        }
        ClassEntity classEntity = classRepository.getReferenceById(classId);
        Session session = new Session();
        session.setClazz(classEntity);
        session.setStartTime(LocalDateTime.now());
        session.setStatus(SessionStatus.OPEN);
        System.out.println(session.getStatus());
        sessionRepository.save(session);

        return ResponseEntity.ok(Map.of("message", "sessão iniciada"));
    }

    @PostMapping("/end")
    public ResponseEntity<?> end() {
        Optional<Session> openSession = sessionRepository.findByStatus(SessionStatus.OPEN);
        openSession.ifPresent(session -> System.out.println(session.getClazz().getName()));

        if (openSession.isEmpty()) {
            return ResponseEntity
                    .status(404)
                    .body("Nenhuma sessão aberta encontrada");
        }
        Session session = openSession.get();
        session.setEndTime(LocalDateTime.now());
        session.setStatus(SessionStatus.CLOSED);

        return ResponseEntity.ok(sessionRepository.save(session));
    }
}
