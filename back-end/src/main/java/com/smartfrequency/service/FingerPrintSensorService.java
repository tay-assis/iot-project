package com.smartfrequency.service;

import com.smartfrequency.model.Attendance;
import com.smartfrequency.model.AttendanceMethod;
import com.smartfrequency.model.Session;
import com.smartfrequency.model.Student;
import com.smartfrequency.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class FingerPrintSensorService {

    @Autowired
    private StudentRepository studentRepository;

    public Attendance getAttendance(Student student, Session session){
        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setMethod(AttendanceMethod.BIOMETRIC);
        attendance.setSession(session);
        attendance.setTimestamp(LocalDateTime.now());

        return attendance;
    }
}
