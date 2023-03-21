package com.healthcare.repository;


import com.healthcare.dto.HealthFacilityDto;
import com.healthcare.dto.MedicineDto;
import com.healthcare.model.HealthFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HealthFacilityRepository extends JpaRepository<HealthFacility, UUID> {

    @Query(value = "select  new com.healthcare.dto.HealthFacilityDto(p.uuid, p.name, p.address, p.latitude, p.longitude, p.staffCount,p.pinCode) from HealthFacility p where p.pinCode = ?1 or p.district.name like %?2%  or p.district.state.name like %?3%")
    List<HealthFacilityDto> getFacilitiesList(Long pin, String district, String state);

    @Query(value = "select  new com.healthcare.dto.HealthFacilityDto(p.uuid, p.name, p.address, p.latitude, p.longitude, p.staffCount,p.pinCode) from HealthFacility p order by p.name ")
    List<HealthFacilityDto> getFacilitiesCompleteList();

    @Query(value = "select hf.uuid , hf.name from health_facility hf where  hf.uuid not  in (select distinct u.health_facility_uuid from user u  where  u.health_facility_uuid  is not null) " , nativeQuery = true)
    List<Object[]> getFacilitiesCompleteListNoAdmin();

}
