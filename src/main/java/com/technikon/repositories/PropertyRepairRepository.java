package com.technikon.repositories;

import com.technikon.exceptions.PropertyRepairException;
import com.technikon.models.PropertyRepair;
import java.time.LocalDateTime;
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
        try {
            TypedQuery<PropertyRepair> query = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
            return query.getResultList();
        } catch (Exception e) {
            throw new PropertyRepairException("Failed to find all PropertyRepairs", e);
        }
    }

    @Override
    public Optional<PropertyRepair> save(PropertyRepair t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            System.out.println("-------------TRIED TO SAVE BUT FAILED");
            entityManager.getTransaction().rollback();
            throw new PropertyRepairException("Failed to save PropertyRepair", e);
        }
        //return Optional.empty();
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
                entityManager.getTransaction().rollback();
                throw new PropertyRepairException("Failed to delete PropertyRepair with id: " + id, e);
                //return false;
            }
            //return true;
        }
        return false;
    }

    public List<PropertyRepair> findBySubmissionDateBetween(String startDate, String endDate) {
        try {
            TypedQuery<PropertyRepair> query = entityManager.createQuery(
                    "SELECT r FROM PropertyRepair r WHERE r.submissionDate BETWEEN :startDate AND :endDate", PropertyRepair.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.getResultList();
        } catch (Exception e) {
            throw new PropertyRepairException("Failed to find PropertyRepairs between dates", e);
        }
    }

    public List<PropertyRepair> findByOwnerId(Long ownerId) {
        try {
            TypedQuery<PropertyRepair> query = entityManager.createQuery(
                    "SELECT r FROM PropertyRepair r WHERE r.property.propertyOwner.ownerId = :ownerId", PropertyRepair.class);
            query.setParameter("ownerId", ownerId);
            return query.getResultList();
        } catch (Exception e) {
            throw new PropertyRepairException("Failed to find PropertyRepairs by owner id: " + ownerId, e);
        }
    }

    private Class<PropertyRepair> getEntityClass() {
        return PropertyRepair.class;
    }

    private String getEntityClassName() {
        return PropertyRepair.class.getName();
    }

}
