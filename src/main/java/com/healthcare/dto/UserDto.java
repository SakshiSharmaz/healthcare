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


}
