package com.tata.demo.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients", schema = "tata")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ClientEntity extends PersonEntity {

//    public ClientEntity() {}
    @Column(length = 20, nullable = false, unique = true)
    private String password;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE")
    private Boolean state;

    @ManyToMany(mappedBy = "clients")
    @JsonIgnoreProperties(value = "clients", allowSetters = true)
    private Set<AccountEntity> accounts  = new HashSet<>();

}
