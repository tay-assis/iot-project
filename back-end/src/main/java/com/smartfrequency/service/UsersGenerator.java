package com.smartfrequency.service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsersGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println("admin: " + encoder.encode("admin"));
        System.out.println("prof: " + encoder.encode("professor"));
    }
}