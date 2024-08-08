package com.technikon.repositories;

import com.technikon.models.PropertyOwner;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * The OwnerRepository class provides CRUD operations for PropertyOwner
 * entities. This class serves as a repository layer between the service layer
 * and the database.
 *
 * @author matina
 */
public class OwnerRepository implements Repository<PropertyOwner, Long> {

    private final EntityManager entityManager;

    public OwnerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Finds a PropertyOwner by their ID.
     *
     * @param id The ID of the PropertyOwner to be retrieved.
     * @return An Optional containing the PropertyOwner if found, or an empty
     * Optional if not.
     */
    @Override
    public Optional<PropertyOwner> findById(Long id) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        if (propertyOwner == null) {
            return Optional.empty();
        }
        return Optional.of(propertyOwner);
    }

    /**
     * Retrieves all PropertyOwner entities from the database.
     *
     * @return A list of all PropertyOwner entities.
     */
    @Override
    public List<PropertyOwner> findAll() {
        TypedQuery<PropertyOwner> query = entityManager.createQuery("from PropertyOwner", PropertyOwner.class);
        return query.getResultList();

    }

    /**
     * Saves a new PropertyOwner or updates an existing one in the database.
     *
     * @param t The PropertyOwner to be saved or updated.
     * @return An Optional containing the saved PropertyOwner if successful, or
     * an empty Optional if not.
     */
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

    /**
     * Deletes a PropertyOwner by their ID.
     *
     * @param id The ID of the PropertyOwner to be deleted.
     * @return true if the PropertyOwner was successfully deleted, false
     * otherwise.
     */
    @Override
    public boolean deleteById(Long id) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        if (propertyOwner != null) {
            try {
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

    /**
     * Finds a PropertyOwner by their email address.
     *
     * @param email The email address of the PropertyOwner to be retrieved.
     * @return The PropertyOwner with the specified email address.
     */
    public PropertyOwner findOwnerByEmail(String email) {
        TypedQuery<PropertyOwner> typedQuery = entityManager.createQuery("from PropertyOwner where email =: data", PropertyOwner.class);
        typedQuery.setParameter("data", email);
        return typedQuery.getSingleResult();
    }

    /**
     * Finds a PropertyOwner by their VAT number.
     *
     * @param vat The VAT number of the PropertyOwner to be retrieved.
     * @return The PropertyOwner with the specified VAT number.
     */
    public PropertyOwner findOwnerByVat(String vat) {
        TypedQuery<PropertyOwner> typedQuery = entityManager.createQuery("from PropertyOwner where vat =: data", PropertyOwner.class);
        typedQuery.setParameter("data", vat);
        return typedQuery.getSingleResult();
    }
}
