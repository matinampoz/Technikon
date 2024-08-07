package com.technikon.services;

import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.repositories.PropertyRepository;
import enums.PropertyType;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    
    private final PropertyRepository propertyRepository;
    
    @Override
    public Property createProperty(String e9, String address, int yearOfConstruction, PropertyType typeOfProperty, PropertyOwner owner) {
        
        Property createdProperty = new Property();
        createdProperty.builder()
                .e9(e9)
                .address(address)
                .yearOfConstruction(yearOfConstruction)
                .typeOfProperty(typeOfProperty)
                .propertyOwner(owner)
                .build();
        
        return createdProperty;
    }
    
    @Override
    public String saveProperty(Property property) {
        
        propertyRepository.save(property);
        return property.getE9();
    }

    @Override
    public boolean deleteProperty(Long id) {
        return propertyRepository.deleteById(id);
    }
    
    @Override
    public List<Property> findPropertiesByVAT(String ownerVat) {

        //PropertyOwnerService ownerService = new PropertyOwnerService();
        PropertyOwner owner = /*ownerService.getOwner(ownerVat);*/ new PropertyOwner();
        
        return propertyRepository.findAll(owner);
    }
    
    @Override
    public Optional<Property> findPropertyByID(Long id) throws InvalidParameterException {
        if (id == null) {
            System.out.println("Null property ID was given");
            throw new InvalidParameterException();
        }
        Optional<Property> property = propertyRepository.findById(id);
        return property;
    }
    
    @Override
    public Optional<Property> findPropertyByE9(String e9) {
        if (e9 == null) {
            System.out.println("Null property E9 was given");
            throw new InvalidParameterException();
        }
        Optional<Property> property = propertyRepository.findById(e9);
        return property;
    }
    
}
