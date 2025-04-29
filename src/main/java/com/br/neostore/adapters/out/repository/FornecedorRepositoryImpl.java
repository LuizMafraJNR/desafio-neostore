package com.br.neostore.adapters.out.repository;

import com.br.neostore.adapters.out.repository.entity.FornecedorEntity;
import com.br.neostore.adapters.out.repository.mapper.FornecedorEntityMapper;
import com.br.neostore.application.exception.BusinessException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional(rollbackOn = Exception.class)
public class FornecedorRepositoryImpl implements FornecedorRepository
{
    private static EntityManagerFactoryUtil emfu = new EntityManagerFactoryUtil();

    @Override
    public FornecedorEntity salvar(FornecedorEntity fornecedor)
    {
        EntityManager em = emfu.getEntityManager();
        try
        {
            em.getTransaction().begin();

            FornecedorEntity entity = fornecedor;

            if ((entity.getId() != null) && (em.find(FornecedorEntity.class, entity.getId()) != null))
            {
                entity = em.merge(entity);
            }
            else
            {
                em.persist(entity);
            }

            em.getTransaction().commit();
            return entity;
        }
        catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new BusinessException("Erro ao salvar fornecedor");
        }
        finally
        {
            em.close();
        }
    }

    @Override
    public void deletarPorId(Long id)
    {
        EntityManager em = emfu.getEntityManager();
        try
        {
            em.getTransaction().begin();

            FornecedorEntity entity = em.find(FornecedorEntity.class, id);
            if (entity != null)
            {
                em.remove(entity);
            }

            em.getTransaction().commit();
        }
        catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new BusinessException("Erro ao deletar fornecedor");
        }
        finally
        {
            em.close();
        }

    }

    @Override
    public Optional<FornecedorEntity> buscarPorId(Long id)
    {
        EntityManager em = emfu.getEntityManager();
        try
        {
            FornecedorEntity entity = em.find(FornecedorEntity.class, id);
            return entity != null ? Optional.of(entity) : Optional.empty();
        }
        finally
        {
            em.close();
        }
    }

    @Override
    public List<FornecedorEntity> listar()
    {
        EntityManager em = emfu.getEntityManager();
        try
        {
            TypedQuery<FornecedorEntity> query = em.createQuery("SELECT f FROM Fornecedor f",
                FornecedorEntity.class);

            return new ArrayList<>(query.getResultList());
        }
        finally
        {
            em.close();
        }
    }

    @Override
    public List<FornecedorEntity> listarPaginado(int page, int size)
    {
        EntityManager em = emfu.getEntityManager();
        try {
            TypedQuery<FornecedorEntity> query = em.createQuery("SELECT f FROM Fornecedor f", FornecedorEntity.class);
            query.setFirstResult(page);
            query.setMaxResults(size);

            return new ArrayList<>(query.getResultList());
        }
        finally {
            em.close();
        }
    }

    @Override
    public long contagem()
    {
        EntityManager em = emfu.getEntityManager();
        try {
            return em.createQuery("SELECT COUNT(f) FROM Fornecedor f", Long.class).getSingleResult();
        }
        finally {
            em.close();
        }
    }
}
