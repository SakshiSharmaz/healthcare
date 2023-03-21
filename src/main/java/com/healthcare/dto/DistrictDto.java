package com.healthcare.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DistrictDto {

    UUID uuid;
    String name;


}
