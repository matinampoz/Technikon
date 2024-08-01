
package com.technikon.repositories;

import com.technikon.models.PropertyOwner;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;


public class OwnerRepository implements Repository<PropertyOwner, Long> {

    private final EntityManager entityManager;

    public OwnerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PropertyOwner> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PropertyOwner> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<PropertyOwner> save(PropertyOwner t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
