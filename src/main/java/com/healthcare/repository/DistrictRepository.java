package com.healthcare.repository;

import com.healthcare.dto.DistrictDto;
import com.healthcare.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {

    @Query( "select  new com.healthcare.dto.DistrictDto(d.uuid, d.name) from District d order by d.name")
    List<DistrictDto> findAllList();
}
