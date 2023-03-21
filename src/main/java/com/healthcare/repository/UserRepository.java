package com.healthcare.repository;

import com.healthcare.dto.UserDto;
import com.healthcare.model.HealthFacility;
import com.healthcare.model.User;
import com.healthcare.model.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findFirstByEmail(String email);
    User findFirstByPhone(String phone);

    UserPrincipal findByUuid(UUID userId);


    @Query(value = "select  new com.healthcare.dto.UserDto(p.uuid, p.firstName, p.lastName, p.email, p.phone) from User p where  p.healthFacility.uuid=?2 and p.userType =?1")
    List<UserDto> findByUserType(String userType, UUID healthFacilityId);
}
