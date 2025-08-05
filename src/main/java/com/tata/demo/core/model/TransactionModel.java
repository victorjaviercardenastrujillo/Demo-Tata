package com.tata.demo.core.model;

import com.tata.demo.core.enums.TransactionType;
import jakarta.persistence.Column;
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
public class TransactionModel {

    private Long id;

    private Date transactionDate = new Date();

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Double balance;

    private Double amount;

    private Boolean initial;

    private Long accountId;

}
