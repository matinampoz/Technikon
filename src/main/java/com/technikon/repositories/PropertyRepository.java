package com.technikon.repositories;

import com.technikon.models.Property;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Pailas
 */
public class PropertyRepository implements Repository<Property, Long> {

    private final EntityManager entityManager;

    public PropertyRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Property> findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Property property = entityManager.find(getEntityClass(), id);
            entityManager.getTransaction().commit();
            return Optional.of(property);
        }catch (Exception e){
            System.out.println("ERROR!!! Property not found...");
        }
        return Optional.empty();
    }

    @Override
    public List<Property> findAll() {
        TypedQuery<Property> query = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }

    @Override
    public Optional<Property> save(Property property) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(property);
            entityManager.getTransaction().commit();
            return Optional.of(property);
        } catch (Exception e) {
            System.out.println("ERROR!!! Could NOT save this item...");
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        Property persistentInstance = entityManager.find(getEntityClass(), id);
        if (persistentInstance != null) {

            try {
                entityManager.getTransaction().begin();
                entityManager.remove(persistentInstance);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("ERROR!!! Could NOT delete this item...");
                return false;
            }
            return true;
        }
        return false;   
    }
    
    private Class<Property> getEntityClass() {
        return Property.class;
    }
    
     private String getEntityClassName() {
        return Property.class.getName();
    }
}
