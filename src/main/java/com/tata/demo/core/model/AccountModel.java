package com.tata.demo.core.model;

import com.tata.demo.core.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {

    private Long id;

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double initialBalance;

    private Boolean state;

    private List<Long> clientsId;

}
