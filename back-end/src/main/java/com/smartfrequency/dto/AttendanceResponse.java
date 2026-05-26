package com.smartfrequency.dto;

import com.smartfrequency.model.AttendanceStatus;

public record AttendanceResponse(Long studentId, String studentName, Long sessionId, AttendanceStatus status
) {
}
