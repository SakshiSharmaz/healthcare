package com.healthcare.repository;

import com.healthcare.model.HealthFacility;
import com.healthcare.model.User;
import com.healthcare.model.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findFirstByEmail(String email);
    User findFirstByPhone(String phone);

    UserPrincipal findByUuid(UUID userId);


}
