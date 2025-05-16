package org.example.repository;

import org.example.model.ContinentD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ContinentDRepository {

    private final EntityManager entityManager;

    public ContinentDRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void create(ContinentD continent) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(continent);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public ContinentD findById(int id) {
        return entityManager.find(ContinentD.class, id);
    }

    public List<ContinentD> findByName(String namePattern) {
        TypedQuery<ContinentD> query = entityManager.createNamedQuery("ContinentD.findByName", ContinentD.class);
        query.setParameter("namePattern", namePattern);
        return query.getResultList();
    }
}