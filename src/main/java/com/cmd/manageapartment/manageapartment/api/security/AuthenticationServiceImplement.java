package com.cmd.manageapartment.manageapartment.api.security;

import com.cmd.manageapartment.manageapartment.api.dto.RegisterDTO;
import com.cmd.manageapartment.manageapartment.api.models.Apartment;
import com.cmd.manageapartment.manageapartment.api.models.Roles;
import com.cmd.manageapartment.manageapartment.api.models.UserEntity;
import com.cmd.manageapartment.manageapartment.api.repository.ApartmentRepository;
import com.cmd.manageapartment.manageapartment.api.repository.RolesRepository;
import com.cmd.manageapartment.manageapartment.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImplement {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegisterDTO registerDto) {
        // Validate if the username already exists
        String username = registerDto.getUsername();
        String password = registerDto.getPassword();
        String apartmentId = registerDto.getApartmentId();

        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("Username already taken");
        }

        // Create a new UserEntity object
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        // Determine role based on the apartmentId
        Roles role;
        if (apartmentId != null && !apartmentId.isEmpty()) {
            // Validate apartmentId
            Apartment apartment = apartmentRepository.findById(UUID.fromString(apartmentId))
                    .orElseThrow(() -> new IllegalStateException("Apartment id is not correct. Please try again."));

            // If apartment is valid, assign OWNER role
            role = rolesRepository.findByName("OWNER")
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        } else{
            // If no apartmentId is provided, assign USER role
            role = rolesRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Role not found"));
        }

        // Set role for the user
        user.setRoles(Collections.singletonList(role));

        // Save the user in the database
        userRepository.save(user);
    }

    private Collection<GrantedAuthority> mapRolesToAuth(List<Roles> roles, String additionalRole) {
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        // Add additional role if provided
        if (additionalRole != null) {
            authorities.add(new SimpleGrantedAuthority(additionalRole));
        }

        return authorities;
    }

}
