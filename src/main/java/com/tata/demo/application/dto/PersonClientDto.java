package com.tata.demo.application.dto;

import com.tata.demo.core.enums.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonClientDto {

    private String dni;

    private String names;

    private String surnames;

    private Date birthday;

    private String address;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    /* Modelo para Entuidad Clilente*/
    private String password;

    private Boolean state;
}
