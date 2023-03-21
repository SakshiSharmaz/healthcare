package com.healthcare.repository;

import com.healthcare.dto.UserDto;
import com.healthcare.model.User;
import com.healthcare.model.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "select  p.uuid, p.first_name, p.last_name, p.email, p.phone,p.health_facility_uuid, hf.name,p.user_type from user p inner join health_facility hf on hf.uuid = p.health_facility_uuid where  p.email = ?1 ", nativeQuery = true)
    List<Object[]> findFirstByEmail(String email);

    @Query(value = "select  p.uuid, p.first_name, p.last_name, p.email, p.phone,p.health_facility_uuid, hf.name,p.user_type from user p inner join health_facility hf on hf.uuid = p.health_facility_uuid where  p.phone = ?1 ", nativeQuery = true)
    List<Object[]> findFirstByPhone(String phone);

    @Query(value = "select  new com.healthcare.dto.UserDto(p.uuid, p.firstName, p.lastName, p.email, p.phone,p.userType ) from User p where  p.email=?1")
    UserDto findFirstByEmailForTLA(String email);

    @Query(value = "select  new com.healthcare.dto.UserDto(p.uuid, p.firstName, p.lastName, p.email, p.phone,p.userType) from User p where  p.phone = ?1 ")
    UserDto findFirstByPhoneForTLA(String phone);

    UserPrincipal findByUuid(UUID userId);

    Boolean existsByEmailOrUsernameOrPhone(String email, String username, String Phone);


    @Query(value = "select  new com.healthcare.dto.UserDto(p.uuid, p.firstName, p.lastName, p.email, p.phone,p.healthFacility.name, p.healthFacility.uuid, p.userType) from User p where  p.healthFacility.uuid=?2 and p.userType =?1")
    List<UserDto> findByUserTypeAndHealthFacilityId(String userType, UUID healthFacilityId);

    @Query(value = "select  new com.healthcare.dto.UserDto(p.uuid, p.firstName, p.lastName, p.email, p.phone,p.healthFacility.name, p.healthFacility.uuid, p.userType) from User p where  p.userType =?1")
    List<UserDto> findByUserType(String userType);


}
