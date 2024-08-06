package com.technikon.services;

import com.technikon.exceptions.OwnerException;
import com.technikon.models.PropertyOwner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    PropertyOwner createOwner(Long vatNumber, String name, String surname, String address, long phoneNumber,
                              String email, String username, String password);

    Long saveOwner(PropertyOwner propertyowner);

    PropertyOwner searchOwnerByEmail(String email) throws OwnerException;

    PropertyOwner searchOwnerByVat(String vatNumber);

    PropertyOwner searchOwnerById(String id) throws OwnerException, NumberFormatException;

    Boolean deleteOwner(String id) throws OwnerException, NumberFormatException;

    List<PropertyOwner> getAllOwners();

}
