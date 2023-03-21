package com.healthcare.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PatientDTO {

    UUID uuid;
    String firstName;
    String lastName;
    String email;
    String phone;

}
