package com.smartfrequency.model;

public enum Role {
    ADMIN("admin"),
    PROFESSOR("professor");

    private String role;

    Role(String role){
        this.role = role;
    }

    public static Role fromAuthority(String authority) {
        return switch (authority) {
            case "ROLE_ADMIN" -> ADMIN;
            case "ROLE_PROFESSOR" -> PROFESSOR;
            default -> throw new IllegalArgumentException("Role inválida: " + authority);
        };
    }

    public String getRole(){
        return role;
    }
}
