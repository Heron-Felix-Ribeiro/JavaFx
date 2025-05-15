package com.example.demo.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtils {

    public static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("desktop");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
