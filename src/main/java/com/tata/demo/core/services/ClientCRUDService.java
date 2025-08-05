package com.tata.demo.core.services;

import com.tata.demo.application.dto.PersonClientDto;
import com.tata.demo.core.model.PersonClientModel;

public interface ClientCRUDService<T> {

    public T findByDNI(String dni);

    public T save(PersonClientDto model);

    public T update(PersonClientModel model);

    public T deleteById(Long id);
}
