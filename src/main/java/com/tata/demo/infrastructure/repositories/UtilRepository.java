package com.tata.demo.infrastructure.repositories;

import com.tata.demo.infrastructure.entities.UtilEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilRepository extends CrudRepository<UtilEntity, Long> {
}
