package com.healthcare.service;

import com.healthcare.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> createHealthFacilityAdmin(User user);

}
