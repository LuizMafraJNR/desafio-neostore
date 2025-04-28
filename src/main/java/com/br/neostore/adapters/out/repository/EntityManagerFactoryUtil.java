package com.br.neostore.adapters.out.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryUtil {
    private static EntityManagerFactoryUtil instance;
    private static final EntityManagerFactory emf;

    private EntityManagerFactoryUtil(){}

    public static EntityManagerFactoryUtil getInstance()
    {
        if (instance == null)
        {
            synchronized (EntityManagerFactoryUtil.class){
                if (instance == null){
                    instance = new EntityManagerFactoryUtil();
                }
            }
        }
        return instance;
    }

    static {
        try {
            emf = Persistence.createEntityManagerFactory("neostore");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar EntityManagerFactory", e);
        }
    }

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }
}
