package com.br.neostore.adapters.out.repository;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerFactoryUtil {

    private static EntityManagerFactory emf = null;

    public EntityManagerFactoryUtil() {
        this.emf = Persistence.createEntityManagerFactory("neostore");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @PreDestroy
    public void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}