package org.example.repository;

import org.example.model.ContinentD;
import jakarta.persistence.EntityManager;

public class ContinentRepository extends AbstractRepository<ContinentD, Integer> {

    public ContinentRepository(EntityManager entityManager) {
        super(entityManager);
    }


}