package com.healthcare.service;

import com.healthcare.model.UserPrincipal;

import java.util.UUID;

public interface CustomUserDetailsService {

    UserPrincipal loadUserById(UUID userId);

}