package com.tata.demo.infrastructure.repositories;

import com.tata.demo.infrastructure.entities.ClientEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    @Query("FROM ClientEntity cl " +
            "WHERE cl.dni = :dni")
    Optional<ClientEntity> findByClientDni(@Param("dni") String dni);
}
