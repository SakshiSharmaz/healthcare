package com.healthcare.repository;

import com.healthcare.model.HealthFacility;
import com.healthcare.model.HistoricalTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface HistoricalTestRepository extends JpaRepository<HistoricalTest, UUID> {
}
