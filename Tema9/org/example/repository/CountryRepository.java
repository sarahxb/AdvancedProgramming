package org.example.repository;

import org.example.model.CountryD;
import jakarta.persistence.EntityManager;

public class CountryRepository extends AbstractRepository<CountryD, Integer> {

    public CountryRepository(EntityManager entityManager) {
        super(entityManager);
    }


}