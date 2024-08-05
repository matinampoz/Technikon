package com.technikon.repositories;

import com.technikon.models.PropertyRepair;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PropertyRepairRepository implements Repository<PropertyRepair, Long> {

    private final EntityManager entityManager;

    public PropertyRepairRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PropertyRepair> findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            PropertyRepair t = entityManager.find(getEntityClass(), id);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            //log.debug("An exception occured");
        }
        return Optional.empty();
    }

    @Override
    public List<PropertyRepair> findAll() {
        TypedQuery<PropertyRepair> query = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }

    @Override
    public Optional<PropertyRepair> save(PropertyRepair t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            // log.debug("An exception occured");
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        PropertyRepair persistentInstance = entityManager.find(getEntityClass(), id);
        if (persistentInstance != null) {

            try {
                entityManager.getTransaction().begin();
                entityManager.remove(persistentInstance);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                //log.debug("An exception occured");
                return false;
            }
            return true;
        }
        return false;
    }

    private Class<PropertyRepair> getEntityClass() {
        return PropertyRepair.class;
    }

    private String getEntityClassName() {
        return PropertyRepair.class.getName();
    }

}
