package com.smartfrequency.controller;

import com.smartfrequency.dto.LoginRequest;
import com.smartfrequency.dto.LoginResponse;
import com.smartfrequency.dto.ProfessorRegisterRequestDTO;
import com.smartfrequency.model.Professor;
import com.smartfrequency.model.Role;
import com.smartfrequency.repository.ProfessorRepository;
import com.smartfrequency.service.TokenService;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {

        System.out.println("login iniciado\n");



        var emailPassword = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        );

        var auth = authenticationManager.authenticate(emailPassword);

        UserDetails user = (UserDetails) auth.getPrincipal();

        var token = tokenService.generateToken(user);

        String authority = user.getAuthorities()
                .iterator()
                .next()
                .getAuthority();


        Role role = Role.fromAuthority(authority);


        return ResponseEntity.ok(new LoginResponse(token,role));
    }
}
