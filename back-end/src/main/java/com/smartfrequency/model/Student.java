package com.smartfrequency.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    @Column(name = "fingerprint_id", unique = true)
    private Long fingerprintId;

    // getters/setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRegistrationNumber() { return registrationNumber; }
    public void setRegistrationNumber(String registrationNumber) { this.registrationNumber = registrationNumber; }
    public Long getFingerprintId() { return fingerprintId; }
    public void setFingerprintId(Long fingerprintId) { this.fingerprintId = fingerprintId; }
}
