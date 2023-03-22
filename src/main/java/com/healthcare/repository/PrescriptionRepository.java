package com.healthcare.repository;

import com.healthcare.dto.PatientDTO;
import com.healthcare.dto.PrescriptionDto;
import com.healthcare.model.HealthFacility;
import com.healthcare.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {

    @Query("select new com.healthcare.dto.PrescriptionDto(p.uuid, p.advice, p.summary,p.createdAt) from Prescription p where p.patient.uuid=?1")
    List<PrescriptionDto> findAllByPatientId(UUID fromString);
}
