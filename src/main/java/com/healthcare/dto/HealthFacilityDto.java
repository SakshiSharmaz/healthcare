package com.healthcare.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class HealthFacilityDto {

    UUID uuid;
    String name;
    String address;
    BigDecimal latitude;
    BigDecimal longitude;
    int staffCount;
    Long pinCode;

}
