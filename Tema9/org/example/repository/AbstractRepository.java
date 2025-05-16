package org.example.repository;

import org.example.utility.LoggerConfig;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractRepository<T, ID extends Serializable> {

    private static final Logger logger = LoggerConfig.getLogger();
    private EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public T findById(Class<T> entityClass, ID id) {
        long startTime = System.currentTimeMillis();
        try {
            T result = entityManager.find(entityClass, id);
            long executionTime = System.currentTimeMillis() - startTime;
            logger.log(Level.INFO, "Executed findById in {0} ms", executionTime);
            return result;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception in findById: {0}", e.getMessage());
            throw e;
        }
    }

    public void persist(T entity) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            long executionTime = System.currentTimeMillis() - startTime;
            logger.log(Level.INFO, "Persisted entity in {0} ms", executionTime);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.log(Level.SEVERE, "Exception in persist: {0}", e.getMessage());
            throw e;
        }
    }

    public void update(T entity) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(entity);
            entityManager.getTransaction().commit();
            long executionTime = System.currentTimeMillis() - startTime;
            logger.log(Level.INFO, "Updated entity in {0} ms", executionTime);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.log(Level.SEVERE, "Exception in update: {0}", e.getMessage());
            throw e;
        }
    }

    public void delete(Class<T> entityClass, ID id) {
        long startTime = System.currentTimeMillis();
        try {
            entityManager.getTransaction().begin();
            T entity = entityManager.find(entityClass, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
            entityManager.getTransaction().commit();
            long executionTime = System.currentTimeMillis() - startTime;
            logger.log(Level.INFO, "Deleted entity in {0} ms", executionTime);
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            logger.log(Level.SEVERE, "Exception in delete: {0}", e.getMessage());
            throw e;
        }
    }
}