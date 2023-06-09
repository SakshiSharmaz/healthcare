package com.healthcare.repository;

import com.healthcare.model.Examination;
import com.healthcare.model.HealthFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, UUID> {
}
