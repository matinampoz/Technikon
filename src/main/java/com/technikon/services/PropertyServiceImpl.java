package com.technikon.services;

import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.repositories.PropertyRepository;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    
    private final PropertyRepository propertyRepository;
    
    @Override
    //TODO: implement with UI
    public Property createProperty() {
        
        Property createdProperty = new Property();

        /*call UI*/
        createdProperty.setE9("123456");
        createdProperty.setAddress("KP 33");
        createdProperty.setYearOfConstruction(2006);
        createdProperty.setPropertyOwner(new PropertyOwner());
        
        return createdProperty;
    }
    
    @Override
    public String saveProperty(Property property) {
        
        propertyRepository.save(property);
        return property.getE9();
    }
    
    @Override
    //TODO: implement with UI
    public Property updateProperty(Property propertyToUpdate) {

        /*call UI*/
        return propertyToUpdate;
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
