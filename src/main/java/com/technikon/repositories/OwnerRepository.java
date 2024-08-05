
package com.technikon.repositories;

import com.technikon.models.PropertyOwner;
import lombok.extern.slf4j.Slf4j;


import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Slf4j
public class OwnerRepository implements Repository<PropertyOwner, Long> {

    private final EntityManager entityManager;

    public OwnerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PropertyOwner> findById(Long id) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        if (propertyOwner == null) return Optional.empty();
        return Optional.of(propertyOwner);
    }

    @Override
    public List<PropertyOwner> findAll() {
        TypedQuery<PropertyOwner> query = entityManager.createQuery("from PropertyOwner", PropertyOwner.class);
        return query.getResultList();

    }

    @Override
    public Optional<PropertyOwner> save(PropertyOwner t) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            //log.debug("An exception occured");
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        if (propertyOwner != null) {
            try{
                entityManager.getTransaction().begin();
                entityManager.remove(propertyOwner);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                System.out.println("An exception occured");
            }
            return true;

        }
        return false;
    }
}
