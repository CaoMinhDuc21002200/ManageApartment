package com.cmd.manageapartment.manageapartment.api.security;


import com.cmd.manageapartment.manageapartment.api.models.Roles;
import com.cmd.manageapartment.manageapartment.api.models.UserEntity;
import com.cmd.manageapartment.manageapartment.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException("Username not found: " + username));

        return new User(user.getUsername(),user.getPassword(),mapRolesToAuth(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapRolesToAuth(List<Roles> roles) {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

    }

}
