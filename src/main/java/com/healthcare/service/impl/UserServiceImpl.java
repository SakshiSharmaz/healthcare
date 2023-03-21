package com.healthcare.service.impl;

import com.healthcare.model.User;
import com.healthcare.repository.UserRepository;
import com.healthcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

   final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<?> createHealthFacilityAdmin(User user) {
        user.setCreatedAt(System.currentTimeMillis());
        user.setUpdatedAt(System.currentTimeMillis());
        repository.save(user);
        return  ResponseEntity.ok("User created successfully");
    }
}
