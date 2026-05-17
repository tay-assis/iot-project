package com.smartfrequency.dto;

import com.smartfrequency.model.AttendanceStatus;

public record AttendanceResponseDTO(Long studentId, Long sessionId, AttendanceStatus status
) {
}