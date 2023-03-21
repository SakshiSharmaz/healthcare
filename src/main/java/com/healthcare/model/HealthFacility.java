package com.healthcare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthcare.Constants;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "health_facility")
@ToString
public class HealthFacility {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = Constants.UUID, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")

    UUID uuid;
    String name;
    String address;
    BigDecimal latitude;
    BigDecimal longitude;
    int staffCount;
    Long pinCode;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "district_uuid", referencedColumnName = "uuid")
    District district;

    @OneToMany(mappedBy="healthFacility")
    private Set<Patient> patients;

    @OneToMany(mappedBy="healthFacility")
    private Set<Medicine> medicines;


    @OneToMany(mappedBy="healthFacility")
    private Set<User> workers;

    @JsonIgnore
    long createdAt;
    @JsonIgnore
    long updatedAt;


}
