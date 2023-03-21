package com.healthcare.service.impl;

import com.healthcare.dto.PatientDTO;
import com.healthcare.model.Patient;
import com.healthcare.model.Prescription;
import com.healthcare.model.User;
import com.healthcare.repository.PatientRepository;
import com.healthcare.repository.PrescriptionRepository;
import com.healthcare.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    PatientRepository repository;
    PrescriptionRepository prescriptionRepository;

    public PatientServiceImpl(PatientRepository repository, PrescriptionRepository prescriptionRepository) {
        this.repository = repository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @Override
    public ResponseEntity<?> createPatient(Patient patient) {
        patient.setCreatedAt(System.currentTimeMillis());
        patient.setUpdatedAt(System.currentTimeMillis());
        repository.save(patient);
        return ResponseEntity.ok("Patient created successfully");
    }

    @Override
    public ResponseEntity<List<PatientDTO>> searchPatients(String mobile, String email) {
        List<PatientDTO> patients;
        if (mobile == null)
            patients = repository.findAllByEmail(email);
        else if (email == null)
            patients = repository.findAllByPhone(mobile);
        else
            patients = repository.findAllByPhoneOrEmail(mobile, email);
//        System.out.println("FOund the problem here");
        return ResponseEntity.ok(patients);
    }

    @Override
    public ResponseEntity<?> prescribe(Prescription prescription) {
            prescriptionRepository.save(prescription);
        return ResponseEntity.ok("Created Prescription successfully");

    }

    @Override
    public ResponseEntity<?> patientList(String uuid) {
        List<PatientDTO> patients;
//        System.out.println(uuid);
            patients = repository.findAllByHealthFacility_Uuid(UUID.fromString(uuid));
        return ResponseEntity.ok(patients);
    }

}
