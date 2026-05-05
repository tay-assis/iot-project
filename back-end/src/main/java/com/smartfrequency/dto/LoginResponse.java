package com.smartfrequency.dto;

import com.smartfrequency.model.Role;

public record LoginResponse(String token, Role role) {
}
