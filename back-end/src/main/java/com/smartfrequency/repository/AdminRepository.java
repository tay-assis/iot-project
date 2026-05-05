package com.smartfrequency.repository;

import com.smartfrequency.model.Admin;
import com.smartfrequency.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    UserDetails findByEmail(String email);
}
