package com.tata.demo.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tata.demo.core.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions", schema = "tata")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP")
    private Date transactionDate = new Date();

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column
    private Double balance;

    @Column
    private Double amount;

    @Column
    private Boolean initial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonIgnoreProperties(value = "transactions")
    private AccountEntity account;

//    public TransactionEntity() {
//    }
//
//    public TransactionEntity(TransactionType transactionType, Double amount, AccountEntity account) {
//        this.transactionType = transactionType;
//        this.amount = amount;
//        this.account = account;
//    }
//
//    public TransactionEntity(
//            TransactionType transactionType
//            , Double balance
//            , Double amount
//            , AccountEntity account
//            ,Boolean initial) {
//        this.transactionDate = new Date();
//        this.transactionType = transactionType;
//        this.balance = balance;
//        this.amount = amount;
//        this.account = account;
//        this.initial = initial;
//    }
//
//    public TransactionEntity(
//            TransactionType transactionType
//            , Double balance
//            , Double amount
//            , AccountEntity account) {
//        this.transactionDate = new Date();
//        this.transactionType = transactionType;
//        this.balance = balance;
//        this.amount = amount;
//        this.account = account;
//        this.initial = false;
//    }
}
