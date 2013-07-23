package com.socialcooking.repository;


import com.socialcooking.repository.api.IGenericRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public abstract class GenericRepositoryImpl<T, K> implements IGenericRepository<T, K> {

    private static final String QUERY_COUNT_ALL = "SELECT COUNT(x) FROM %s x";
    private static final String QUERY_SELECT_ALL = "SELECT x FROM %s x";
    private static final String QUERY_SELECT_BY_ID_LIST = "SELECT x FROM %s x WHERE x.id IN (?1)";

    @PersistenceContext
    private EntityManager entityManager;

    protected Class<T> persistentClass;

    protected GenericRepositoryImpl(Class<T> persistentClass) {
        super();
        this.persistentClass = persistentClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    protected <REZ> REZ executeQuery(String queryOrQueryName,
                                     boolean namedQuery, boolean singleResult, Object... parameters) {


        if (queryOrQueryName == null) {
            throw new IllegalArgumentException(
                    "Query for executing cannot be null");
        }

        REZ result;
        List<?> list;

        Query query;
        if (namedQuery) {
            query = entityManager.createNamedQuery(queryOrQueryName);
        } else {
            query = entityManager.createQuery(queryOrQueryName);
        }

        if (parameters.length > 0) {
            for (int i = 0; i < parameters.length; i++) {
                query.setParameter(i + 1, parameters[i]);
            }
        }


        if (singleResult) {
            if (query != null) {
                result = (REZ) query.getSingleResult();
            } else {
                result = null;
            }
        } else {
            result = (REZ) query.getResultList();
        }

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getAllEntitiesCount() {
        try {
            return executeQuery(String.format(QUERY_COUNT_ALL, persistentClass.getSimpleName()), false, true);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public T save(T entity) throws IllegalArgumentException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity for saving cannot be null!");
        }
        entityManager.persist(entity);
        return entity;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public T update(T entity) throws IllegalArgumentException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity for saving cannot be null!");
        }
        entityManager.merge(entity);
        return entity;
    }

    //
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    protected int executeUpdateQuery(final String queryOrQueryName,
//                                     final boolean namedQuery, final Object... parameters)
//            throws IllegalArgumentException {
//
//        if (queryOrQueryName == null) {
//            throw new IllegalArgumentException(
//                    "Query for executing cannot be null");
//        }
//
//        Object rez;
//
//        Query query;
//        if (namedQuery) {
//            query = em.createNamedQuery(queryOrQueryName);
//        } else {
//            query = em.createQuery(queryOrQueryName);
//        }
//
//        if (parameters.length > 0) {
//            for (int i = 0; i < parameters.length; i++) {
//                query.setParameter(i + 1, parameters[i]);
//            }
//        }
//
//        rez = Integer.valueOf(query.executeUpdate());
//
//
//        return ((Integer) rez).intValue();
//
//    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public T saveOrUpdate(T entity) throws IllegalArgumentException {
//        if (entity == null) {
//            throw new IllegalArgumentException(
//                    "Entity for saving cannot be null!");
//        }
//
//        T savedEntity = null;
//
//        try {
//            if (entity.getId() == null) {
//                jpaTemplate.persist(entity);
//                savedEntity = entity;
//            } else {
//                savedEntity = jpaTemplate.merge(entity);
//            }
//        } catch (Exception e) {
//            if (entity.getId() == null) {
//                throw new GeneralServiceException(
//                        "GenericServiceImpl.ErrorFailedToAdd"
//                                + entity.getClass().getSimpleName(), e);
//            } else {
//                throw new GeneralServiceException(
//                        "GenericServiceImpl.ErrorFailedToUpdate"
//                                + entity.getClass().getSimpleName()
//                                + "GenericServiceImpl.WithId" + entity.getId(),
//                        e);
//            }
//        }
//
//        return savedEntity;
//    }
//
//    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
//    public void delEntity(Long id) throws IllegalArgumentException,
//            GeneralServiceException {
//        if (id == null) {
//            throw new IllegalArgumentException("ErrorIdNull");
//        }
//
//        // ïðîâåðÿåì åñòü ëè òàêîé îáúåêò âîîáùå â áàçå
//        T savedEntity = getEntityById(id);
//
//        if (savedEntity != null) {
//            // äåëåãèðóåì íà ìåòîä óäëàåíèÿ ïî ñóùíîñòè
//            delEntity(savedEntity);
//        }
//    }


}
