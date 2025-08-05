package com.tata.demo.core.services;

import com.tata.demo.application.dto.AccountDto;
import com.tata.demo.core.model.AccountModel;
import com.tata.demo.infrastructure.entities.AccountEntity;

import java.util.Optional;

public interface AccountService<T> {

    public Iterable<?> findAll();

    public Optional<?> findById(Long id);

    public T findByAccountNumber(String accountNumber);

    public T save(AccountModel account);

    public T update(AccountModel account);

    public T deleteById(Long id);
}
