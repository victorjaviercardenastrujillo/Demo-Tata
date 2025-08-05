package com.tata.demo.core.services;

import com.tata.demo.core.model.TransactionModel;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionService<T> {

    public Iterable<?> findAll();

    public Optional<?> findById(Long id);

    public T save(TransactionModel transaction);

    public void deleteById(Long id);

    public List<T> accountStatus(String  accountNumber, Date initDate, Date endDate) throws ParseException;
}
