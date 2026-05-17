package com.smartfrequency.dto;

import com.smartfrequency.model.AttendanceStatus;

public record UpdateAttendanceRequestDTO(Long studentId, Long sessionId, AttendanceStatus status
) {
}