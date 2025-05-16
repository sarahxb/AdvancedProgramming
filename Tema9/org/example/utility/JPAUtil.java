package org.example.utility;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {


    private static JPAUtil instance;


    private EntityManagerFactory entityManagerFactory;


    private JPAUtil() {

        this.entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }


    public static synchronized JPAUtil getInstance() {
        if (instance == null) {
            instance = new JPAUtil();
        }
        return instance;
    }


    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }


    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}