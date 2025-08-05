package com.tata.demo.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tata.demo.core.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts", schema = "tata"
//        ,uniqueConstraints = { @UniqueConstraint(
//                name = "",columnNames = { "accountNumber", "initialBalance" })}
)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", length = 20, nullable = false, unique = true)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "initial_balance")
    private Double initialBalance;

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE")
    private Boolean state;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "account_client", schema = "tata",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "client_id") }
    )
    @JsonIgnoreProperties(value = "accounts", allowSetters = true)
    private List<ClientEntity> clients;

    @OneToMany(
            mappedBy = "account",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties(value = "account", allowSetters = true)
    private List<TransactionEntity> transactions;

//    public Account() {
//    }
//
//    public void addClient(ClientEntity client) {
//        this.clients.add(client);
//        client.getAccounts().add(this);
//    }
//
//    public void removeClient(ClientEntity client) {
//        this.clients.remove(client);
//        client.getAccounts().remove(this);
//    }

//    public void setAddresses(List<Client> clients) {
//        this.clients.clear();
//        clients.forEach(this::addClient);
//    }

}
