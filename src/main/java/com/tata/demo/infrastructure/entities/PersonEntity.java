package com.tata.demo.infrastructure.entities;

import com.tata.demo.core.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "Person", schema = "tata")
@Inheritance(strategy = InheritanceType.JOINED) // También podrías usar SINGLE_TABLE o TABLE_PER_CLASS
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 13, nullable = false, unique = true)
    private String dni;

    @Column(length = 50, nullable = false)
    private String names;

    @Column(length = 50, nullable = false)
    private String surnames;

    @Column(nullable = false)
    private Date birthday;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;



}
