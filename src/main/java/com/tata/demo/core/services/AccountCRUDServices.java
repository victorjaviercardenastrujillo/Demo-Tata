package com.tata.demo.core.services;

import com.tata.demo.core.model.AccountModel;

import java.util.Optional;

public interface AccountCRUDServices<T> {

    public Iterable<?> findAll();

    public Optional<?> findById(Long id);

    public T save(AccountModel model);

    public T update(AccountModel model);

    public T deleteById(Long id);

}
