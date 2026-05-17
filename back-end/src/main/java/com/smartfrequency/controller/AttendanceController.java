package com.smartfrequency.controller;

import com.smartfrequency.dto.AttendanceRequest;
import com.smartfrequency.dto.AttendanceResponse;
import com.smartfrequency.dto.AttendanceResponseDTO;
import com.smartfrequency.dto.UpdateAttendanceRequestDTO;
import com.smartfrequency.model.*;
import com.smartfrequency.repository.AttendanceRepository;
import com.smartfrequency.repository.EnrollmentRepository;
import com.smartfrequency.repository.SessionRepository;
import com.smartfrequency.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // 📡 ESP32 envia presença (UMA por vez)
    @PostMapping("/device")
    public ResponseEntity<?> registerFromDevice(@RequestBody AttendanceRequest attendanceRequest) {
        System.out.println("id enviado do esp :"+ attendanceRequest.fingerId());

        Session session = sessionRepository.findByStatus(SessionStatus.OPEN)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        // encontra aluno pelo fingerprintId
        Student student = studentRepository
                .findByFingerprintId(attendanceRequest.fingerId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        System.out.println(student.getName());

        // valida se aluno pertence à turma
        boolean enrolled = enrollmentRepository
                .existsByStudent_IdAndClazz_Id(
                        student.getId(),
                        session.getClazz().getId()
                );

        if (!enrolled) {
            return ResponseEntity.badRequest().body("Student not enrolled in this class");
        }

        // evita duplicidade
        boolean alreadyExists = attendanceRepository
                .existsBySessionIdAndStudentId(session.getId(), student.getId());

        if (alreadyExists) {
            return ResponseEntity.ok("Already registered");
        }
        // salva presença
        Attendance attendance = new Attendance();
        attendance.setSession(session);
        attendance.setStudent(student);
        attendance.setTimestamp(LocalDateTime.now());
        attendance.setStatus(AttendanceStatus.PRESENT);
        attendance.setMethod(AttendanceMethod.BIOMETRIC);

        attendanceRepository.save(attendance);

        return ResponseEntity.ok("Attendance recorded");
    }

    // Professor pode editar manualmente
    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> createAttendance(
            @RequestBody UpdateAttendanceRequestDTO request
    ) {

        Student student = studentRepository
                .getReferenceById(request.studentId());

        Session session = sessionRepository
                .getReferenceById(request.sessionId());

        Attendance attendance = new Attendance();

        attendance.setStudent(student);
        attendance.setSession(session);
        attendance.setStatus(request.status());
        attendance.setMethod(AttendanceMethod.MANUAL);

        Attendance saved =
                attendanceRepository.save(attendance);

        return ResponseEntity.ok(
                new AttendanceResponseDTO(
                        saved.getStudent().getId(),
                        saved.getSession().getId(),
                        saved.getStatus()
                )
        );
    }
    // 📊 Listar presença da sessão (para dashboard)
    @GetMapping("/session/{classId}")
    public ResponseEntity<List<AttendanceResponse>> getSessionAttendance(
            @PathVariable Long classId
    ) {

        List<Student> students = enrollmentRepository
                .findByClazz_Id(classId)
                .stream()
                .map(Enrollment::getStudent)
                .toList();

        Optional<Session> optionalSession =
                sessionRepository
                        .findByClazz_IdAndStatus(
                                classId,
                                SessionStatus.OPEN
                        );

        List<AttendanceResponse> result = new ArrayList<>();

        // não existe sessão aberta
        if (optionalSession.isEmpty()) {

            for (Student student : students) {

                result.add(
                        new AttendanceResponse(
                                student.getId(),
                                student.getName(),
                                null,
                                AttendanceStatus.ABSENT
                        )
                );
            }

            return ResponseEntity.ok(result);
        }

        // existe sessão aberta
        Session session = optionalSession.get();

        List<Attendance> attendances =
                attendanceRepository
                        .findBySessionId(session.getId());

        Map<Long, Attendance> map = attendances.stream()
                .collect(Collectors.toMap(
                        a -> a.getStudent().getId(),
                        a -> a
                ));

        for (Student student : students) {

            Attendance attendance =
                    map.get(student.getId());

            AttendanceStatus status =
                    attendance != null
                            ? attendance.getStatus()
                            : AttendanceStatus.ABSENT;

            result.add(
                    new AttendanceResponse(
                            student.getId(),
                            student.getName(),
                            session.getId(),
                            status
                    )
            );
        }

        return ResponseEntity.ok(result);
    }
}
