package com.healthcare.model;

import com.healthcare.Constants;
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

@Table(name = "examination")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Examination {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = Constants.UUID, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID uuid;
    String name;
    String description;
    @ManyToMany(mappedBy = "examinations")
    private Set<Prescription> prescriptions = new HashSet<>();
    long createdAt;
    long updatedAt;




}
