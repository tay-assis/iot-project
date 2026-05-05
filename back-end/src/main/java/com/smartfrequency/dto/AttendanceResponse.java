package com.smartfrequency.dto;

import com.smartfrequency.model.AttendanceStatus;

public record AttendanceResponse(Long sessionId, Long fingerprintId, AttendanceStatus status) {
}
