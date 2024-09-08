package com.cmd.manageapartment.manageapartment.api.controller;


import com.cmd.manageapartment.manageapartment.api.dto.AuthResponseDTO;
import com.cmd.manageapartment.manageapartment.api.dto.ErrorResponseDTO;
import com.cmd.manageapartment.manageapartment.api.dto.LoginDTO;
import com.cmd.manageapartment.manageapartment.api.dto.RegisterDTO;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.repository.RolesRepository;
import com.cmd.manageapartment.manageapartment.api.repository.UserRepository;
import com.cmd.manageapartment.manageapartment.api.security.AuthenticationServiceImplement;
import com.cmd.manageapartment.manageapartment.api.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final ApartmentRepository apartmentRepository;

    private final AuthenticationManager authenticationManager;

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository;

    private final JWTGenerator jwtGenerator;

    private final AuthenticationServiceImplement authenticationService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          RolesRepository rolesRepository,
                          JWTGenerator jwtGenerator,
                          AuthenticationServiceImplement authenticationService,
                          ApartmentRepository apartmentRepository) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolesRepository = rolesRepository;
        this.jwtGenerator = jwtGenerator;
        this.authenticationService = authenticationService;
        this.apartmentRepository = apartmentRepository;
    }

    @PostMapping("register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        if (registerDto.getApartmentId()==null) {
            registerDto.setApartmentId("");
            authenticationService.registerUser(registerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully.");
        }
        if (apartmentRepository.findById(UUID.fromString(registerDto.getApartmentId()))
                .isEmpty()) {
            return new ResponseEntity<>("Apartment Id is not correct. Please try again."
                    ,HttpStatus.BAD_REQUEST);
        }
        authenticationService.registerUser(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered Successfully.");
    }

    @PostMapping("login")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getUsername(),
                            loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            AuthResponseDTO authResponseDto = new AuthResponseDTO(token);
            return ResponseEntity.ok(authResponseDto);
        }catch (AuthenticationException e) {

            // Log the exception and return an appropriate response
            logger.info("Authentication failed for user: " + loginDto.getUsername() + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDTO("Incorrect username or password."));

        }

    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<String> getAdminDashboard() {
        return ResponseEntity.ok("Welcome to the Admin Dashboard");
    }


}
