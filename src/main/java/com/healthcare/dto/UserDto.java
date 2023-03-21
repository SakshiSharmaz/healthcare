package com.healthcare.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {

    UUID uuid;
    String firstName;
    String lastName;
    String email;
    String phone;



    String healthFacilityName;

    UUID healthFacilityUUid ;
    String userType;


    public UserDto(UUID uuid, String firstName, String lastName, String email, String phone, String userType) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }
}
