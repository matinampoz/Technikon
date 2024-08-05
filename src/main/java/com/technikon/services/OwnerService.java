package com.technikon.services;

import com.technikon.models.PropertyOwner;

import java.util.Optional;

public interface OwnerService {
    PropertyOwner createOwner(Long vatNumber, String name, String surname, String address, long phoneNumber,
                              String email, String username, String password);

    Long saveOwner(PropertyOwner propertyowner);

    PropertyOwner searchOwnerByEmail(String email);

    PropertyOwner searchOwnerByVat(String vatNumber);

    Optional<PropertyOwner> searchOwnerById(String id);

    Boolean deleteOwner(String id);
}
