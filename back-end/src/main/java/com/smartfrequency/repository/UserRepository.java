package com.smartfrequency.repository;

import com.smartfrequency.model.Professor;
import com.smartfrequency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
