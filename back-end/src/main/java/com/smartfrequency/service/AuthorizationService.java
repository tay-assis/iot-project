package com.smartfrequency.service;

import com.smartfrequency.repository.AdminRepository;
import com.smartfrequency.repository.ProfessorRepository;
import com.smartfrequency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        System.out.println(email);
        UserDetails user = userRepository.findByEmail(email);

        if(user == null){
            System.out.println("email não encontrado");
        }

        return userRepository.findByEmail(email);
    }
}
