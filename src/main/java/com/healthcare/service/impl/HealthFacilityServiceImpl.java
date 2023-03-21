package com.healthcare.service.impl;

import com.healthcare.dto.HFMinDto;
import com.healthcare.model.HealthFacility;
import com.healthcare.repository.DistrictRepository;
import com.healthcare.repository.HealthFacilityRepository;
import com.healthcare.service.FacilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HealthFacilityServiceImpl implements FacilityService {


    HealthFacilityRepository repository;
    DistrictRepository districtRepository;

    public HealthFacilityServiceImpl(HealthFacilityRepository repository, DistrictRepository districtRepository) {
        this.repository = repository;
        this.districtRepository = districtRepository;
    }

    @Override
    public ResponseEntity<?> createHealthFacility(HealthFacility facility) {
        facility.setCreatedAt(System.currentTimeMillis());
        facility.setUpdatedAt(System.currentTimeMillis());
        repository.save(facility);
        return ResponseEntity.ok("Health Facility created successfully");
    }

    @Override
    public ResponseEntity<?> searchHealthFacility(Long pincode, String state, String district) {
        return ResponseEntity.ok(repository.getFacilitiesList(pincode, district, state));

    }

    @Override
    public ResponseEntity<?> getHealthFacilityList() {
        return ResponseEntity.ok(repository.getFacilitiesCompleteList());

    }

    @Override
    public ResponseEntity<?> getDistrictList() {
        return ResponseEntity.ok(districtRepository.findAllList());
    }

    @Override
    public ResponseEntity<?> getHealthFacilityListWithNoAdmin() {
        List<Object[]> facilitiesCompleteListNoAdmin = repository.getFacilitiesCompleteListNoAdmin();
        List<HFMinDto> list = new ArrayList<>();
        facilitiesCompleteListNoAdmin.forEach(item -> list.add(new HFMinDto(UUID.fromString((String) item[0]), (String) item[1])));
        return ResponseEntity.ok(list);
    }
}
