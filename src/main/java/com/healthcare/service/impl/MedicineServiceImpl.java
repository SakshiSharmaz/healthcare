package com.healthcare.service.impl;

import com.healthcare.dto.MedicineDto;
import com.healthcare.model.Medicine;
import com.healthcare.repository.MedicineRepository;
import com.healthcare.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MedicineServiceImpl implements MedicineService {

    MedicineRepository repository;

    public MedicineServiceImpl(MedicineRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseEntity<?> addNewMedicine(Medicine medicine) {
        medicine.setCreatedAt(System.currentTimeMillis());
        medicine.setUpdatedAt(System.currentTimeMillis());
        repository.save(medicine);
        return ResponseEntity.ok("Medicine created successfully");

    }

    @Override
    public ResponseEntity<?> updateMedicineCount(UUID uuid, int count) {
        repository.updateStockCount(uuid, count);
        return ResponseEntity.ok("Medicine stock count updated successfully");
    }

    @Override
    public ResponseEntity<?> searchMedicine(String paramValue) {
        List<MedicineDto> medicineList = repository.getMedicineList(paramValue);
        return ResponseEntity.ok(medicineList);
    }

    @Override
    public ResponseEntity<?> allMedicines() {
        List<MedicineDto> medicineList = repository.allMedicines();
        return ResponseEntity.ok(medicineList);
    }
}
