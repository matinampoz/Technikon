/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.technikon.repositories;

import com.technikon.models.PropertyOwner;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author zouka
 */
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
