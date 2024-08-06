package com.technikon.services;

import com.technikon.exceptions.PropertyException;
import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.repositories.PropertyRepository;
import enums.PropertyType;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class PropertyServiceImpl implements PropertyService {

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    private PropertyRepository propertyRepository;

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
    public String saveProperty(Property property) throws PropertyException {

        Optional<Property> savedProperty = propertyRepository.save(property);
        if (savedProperty.isEmpty()) {
            throw new PropertyException("!!!>>>Error while saving the Property!!!");
        } else {
            return savedProperty.get().getE9();
        }
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
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> findPropertiesByVAT(Long ownerVat) {

//        OwnerService ownerService = new OwnerServiceImpl(new OwnerRepository(JpaUtil.getEntityManager()));
//        PropertyOwner owner = ownerService.searchOwnerByVat(ownerVat);
//        return propertyRepository.findAll(owner);
        List<Property> allProperties = propertyRepository.findAll();
        allProperties.stream()
                .filter(property -> property.getPropertyOwner().getVatNumber() == ownerVat)
                .collect(Collectors.toList());
        return allProperties;
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
