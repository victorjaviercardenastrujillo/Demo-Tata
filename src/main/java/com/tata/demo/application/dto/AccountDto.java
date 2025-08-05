package com.tata.demo.application.dto;

import com.tata.demo.core.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Double initialBalance;

    private Boolean state;

    private Long clientId;
}
