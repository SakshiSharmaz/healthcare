package com.healthcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthcare.Constants;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = Constants.UUID, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")

    UUID uuid;
    String firstName;
    String lastName;
    String email;
    String phone;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "health_facility_uuid", referencedColumnName = "uuid")
    HealthFacility healthFacility;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "historical_test_uuid", referencedColumnName = "uuid")
    HistoricalTest historicalTest;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "patient")
    Set<Prescription> prescriptions = new HashSet<>();

    @JsonIgnore
    long createdAt;
    @JsonIgnore
    long updatedAt;


}
