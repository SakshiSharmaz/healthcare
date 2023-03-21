package com.healthcare.repository;

import com.healthcare.dto.PatientDTO;
import com.healthcare.model.HealthFacility;
import com.healthcare.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    @Query(value = "select  new com.healthcare.dto.PatientDTO(p.uuid, p.firstName, p.lastName, p.email, p.phone) from Patient p where p.phone=?1 or p.email=?2")
    List<PatientDTO> findAllByPhoneOrEmail(String phone, String email);
    @Query(value = "select  new com.healthcare.dto.PatientDTO(p.uuid, p.firstName, p.lastName, p.email, p.phone) from Patient p where p.phone=?1 ")
    List<PatientDTO> findAllByPhone(String phone);
    @Query(value = "select  new com.healthcare.dto.PatientDTO(p.uuid, p.firstName, p.lastName, p.email, p.phone) from Patient p where  p.email=?1")
    List<PatientDTO> findAllByEmail(String email);

}
