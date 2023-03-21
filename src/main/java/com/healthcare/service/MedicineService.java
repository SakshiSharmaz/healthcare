package com.healthcare.service;

import com.healthcare.model.Medicine;
import com.healthcare.model.User;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface MedicineService {

    ResponseEntity<?> addNewMedicine(Medicine medicine);
    ResponseEntity<?> updateMedicineCount(UUID uuid, int count);

    ResponseEntity<?> searchMedicine(String paramValue);

    ResponseEntity<?> allMedicines();
}
