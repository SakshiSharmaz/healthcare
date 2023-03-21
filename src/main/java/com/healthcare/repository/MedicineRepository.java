package com.healthcare.repository;

import com.healthcare.dto.MedicineDto;
import com.healthcare.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    @Modifying
    @Query("update Medicine u set u.stock = ?2 where u.uuid = ?1")
    void updateStockCount(UUID uuid, int count);

    @Query(value = "select  new com.healthcare.dto.MedicineDto(p.uuid, p.name, p.category, p.stock, p.isAvailable) from Medicine p where p.name like %?1% ")
    List<MedicineDto> getMedicineList(String paramValue);

    @Query(value = "select  new com.healthcare.dto.MedicineDto(p.uuid, p.name, p.category, p.stock, p.isAvailable) from Medicine p  ")
    List<MedicineDto> allMedicines();

}
