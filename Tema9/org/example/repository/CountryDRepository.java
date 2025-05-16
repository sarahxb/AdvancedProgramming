package org.example.repository;

import org.example.model.CountryD;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CountryDRepository {

    private final EntityManager entityManager;

    public CountryDRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public void create(CountryD country) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(country);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public CountryD findById(int id) {
        return entityManager.find(CountryD.class, id);
    }


    public List<CountryD> findByName(String namePattern) {
        TypedQuery<CountryD> query = entityManager.createNamedQuery("CountryD.findByName", CountryD.class);
        query.setParameter("namePattern", namePattern);
        return query.getResultList();
    }
}