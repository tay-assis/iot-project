package com.smartfrequency.controller;

import com.smartfrequency.dto.AttendanceRequest;
import com.smartfrequency.dto.AttendanceResponse;
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
    @PutMapping
    public ResponseEntity<Attendance> updateAttendance(@RequestBody Attendance attendance) {
        attendance.setMethod(AttendanceMethod.MANUAL);
        return ResponseEntity.ok(attendanceRepository.save(attendance));
    }

    // 📊 Listar presença da sessão (para dashboard)
    @GetMapping("/session")
    public ResponseEntity<List<AttendanceResponse>> getSessionAttendance() {

        Session session = sessionRepository.findByStatus(SessionStatus.OPEN)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        List<Student> students = enrollmentRepository
                .findByClazz_Id(session.getClazz().getId())
                .stream()
                .map(Enrollment::getStudent)
                .toList();

        List<Attendance> attendances = attendanceRepository
                .findBySessionId(session.getId());

        Map<Long, Attendance> map = attendances.stream()
                .collect(Collectors.toMap(
                        a -> a.getStudent().getId(),
                        a -> a
                ));

        List<AttendanceResponse> result = new ArrayList<>();

        for (Student student : students) {

            Attendance attendance = map.get(student.getId());

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
