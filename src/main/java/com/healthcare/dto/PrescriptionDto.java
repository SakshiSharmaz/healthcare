package com.healthcare.dto;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PrescriptionDto {

    UUID uuid;
    String advice;
    String summary;
    long createdAt;

}
