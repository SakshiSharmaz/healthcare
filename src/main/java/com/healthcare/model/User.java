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
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = Constants.UUID, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")

    UUID uuid;
    String userType;
    String firstName;
    String lastName;
    String email;
    String phone;
    String username;
    String password;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "health_facility_uuid", referencedColumnName = "uuid")
    HealthFacility healthFacility;


    @JsonIgnore
    long createdAt;
    @JsonIgnore
    long updatedAt;




}
