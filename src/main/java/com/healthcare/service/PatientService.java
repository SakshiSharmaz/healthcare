package com.healthcare.service;

import com.healthcare.dto.PatientDTO;
import com.healthcare.model.Patient;
import com.healthcare.model.Prescription;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {

    public ResponseEntity<?> createPatient(Patient patient);

    ResponseEntity<List<PatientDTO>> searchPatients(String mobile, String email);
    ResponseEntity<?> prescribe(Prescription prescription);


}
