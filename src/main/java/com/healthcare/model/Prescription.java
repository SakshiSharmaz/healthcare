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
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = Constants.UUID, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID uuid;

    String summary;
    String advice;

    @ManyToOne
    @JoinColumn(name="patient_uuid", nullable=false)
    Patient patient;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "medicine_map_prescription",
            joinColumns = { @JoinColumn(name = "prescription_uuid") },
            inverseJoinColumns = { @JoinColumn(name = "medicine_uuid") }
    )
    Set<Medicine> medicines = new HashSet<>();


    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "examination_map_prescription",
            joinColumns = { @JoinColumn(name = "prescription_uuid") },
            inverseJoinColumns = { @JoinColumn(name = "examination_uuid") }
    )
    Set<Examination> examinations = new HashSet<>();


    long createdAt;
    long updatedAt;



}
