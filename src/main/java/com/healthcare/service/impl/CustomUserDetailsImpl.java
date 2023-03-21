package com.healthcare.service.impl;

import com.healthcare.model.UserPrincipal;
import com.healthcare.repository.UserRepository;
import com.healthcare.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUserDetailsImpl implements UserDetailsService, CustomUserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserPrincipal loadUserById(UUID userId) {

        return userRepository.findByUuid(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }



}
