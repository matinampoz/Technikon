/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.technikon.repositories;

import com.technikon.models.PropertyRepair;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;

/**
 *
 * @author zouka
 */
public class PropertyRepairRepository implements Repository<PropertyRepair, Long> {

    private final EntityManager entityManager;

    public PropertyRepairRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PropertyRepair> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PropertyRepair> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<PropertyRepair> save(PropertyRepair t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
