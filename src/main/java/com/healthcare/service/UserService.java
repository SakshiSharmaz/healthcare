package com.healthcare.service;

import com.healthcare.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> createHealthFacilityAdmin(User user);

    ResponseEntity<?> login(User user);

    ResponseEntity<?> getUserList(String userType, String healthFacilityId);
}
