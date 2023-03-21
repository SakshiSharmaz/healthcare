package com.healthcare.dto;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MedicineDto {
    UUID uuid;
    String name;
    String category;
    int stock;
    boolean isAvailable;
}
