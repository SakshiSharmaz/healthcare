package com.healthcare.repository;

import com.healthcare.model.HealthFacility;
import com.healthcare.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
}
