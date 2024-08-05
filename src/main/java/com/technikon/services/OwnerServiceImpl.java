package com.technikon.services;

import com.technikon.models.PropertyOwner;
import com.technikon.repositories.OwnerRepository;

import java.util.List;
import java.util.Optional;

public class OwnerServiceImpl implements OwnerService{
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public PropertyOwner createOwner(Long vatNumber, String name, String surname, String address, long phoneNumber,
                              String email, String username, String password){

        return PropertyOwner.builder()
                .vatNumber(vatNumber)
                .name(name)
                .surname(surname)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .username(username)
                .password(password)
                .build();
    }

    @Override
    public Long saveOwner(PropertyOwner propertyowner) {
        ownerRepository.save(propertyowner);
        return propertyowner.getOwnerId();
    }

    @Override
    public PropertyOwner searchOwnerByEmail(String email) {
        List<PropertyOwner> owners = ownerRepository.findAll();
        for (PropertyOwner owner : owners) {
            if (owner.getEmail().equalsIgnoreCase(email)) {
                return owner;
            }
        }
        return null;
    }

    @Override
    public PropertyOwner searchOwnerByVat(String vatNumber) {
        try {
            long vatNumberLong = Long.parseLong(vatNumber);
            List<PropertyOwner> owners = ownerRepository.findAll();
            for (PropertyOwner owner : owners) {
                if (owner.getVatNumber() == vatNumberLong) {
                    return owner;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid VAT number format: " + vatNumber);
        }
        return null;
    }

    @Override
    public Optional<PropertyOwner> searchOwnerById(String id) {
        try {
            Long ownerId = Long.parseLong(id);
            return ownerRepository.findById(ownerId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + id);
            return Optional.empty();
        }
    }


    @Override
    public Boolean deleteOwner(String id) {
        try {
            Long ownerId = Long.parseLong(id);
            return ownerRepository.deleteById(ownerId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format: " + id);
            return false;
        }
    }
}
