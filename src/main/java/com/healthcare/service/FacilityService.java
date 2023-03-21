package com.healthcare.service;

import com.healthcare.model.HealthFacility;
import com.healthcare.model.User;
import org.springframework.http.ResponseEntity;

public interface FacilityService {

    ResponseEntity<?> createHealthFacility(HealthFacility facility);

    ResponseEntity<?> searchHealthFacility(Long pincode, String state, String district);
}
