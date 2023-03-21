package com.healthcare.model;

import com.healthcare.Constants;
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
@Table(name = "historical_test")
public class HistoricalTest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = Constants.UUID, unique = true, columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    UUID uuid;
    boolean hypertension;
    boolean heartDisease;
    boolean diabetes;
    boolean asthma;
    boolean cancer;
    boolean kidneyDisease;
    boolean tuberculosis;

    long createdAt;
    long updatedAt;



}
