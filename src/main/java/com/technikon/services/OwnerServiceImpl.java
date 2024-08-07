package com.technikon.services;

import com.technikon.exceptions.OwnerException;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.OwnerRepository;

import java.util.List;
import java.util.Optional;

public class OwnerServiceImpl implements OwnerService{

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public PropertyOwner createOwner(String vatNumber, String name, String surname, String address, long phoneNumber,
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
    public List<PropertyOwner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public PropertyOwner searchOwnerByEmail(String email) throws OwnerException {
        if (email == null || !email.contains("@")) {
            throw new OwnerException("Invalid email");
        }
        return ownerRepository.findOwnerByEmail(email);
    }

    @Override
    public PropertyOwner searchOwnerByVat(String vatNumber) throws OwnerException {
        if (vatNumber == null){
            throw new OwnerException("Invalid vat number");
        }
        return ownerRepository.findOwnerByVat(vatNumber);
    }

    @Override
    public PropertyOwner searchOwnerById(String id) throws OwnerException, NumberFormatException{
            if (id == null) {
                throw new OwnerException("Invalid id");
            }
            Long ownerId = Long.parseLong(id);
            Optional<PropertyOwner> owner = ownerRepository.findById(ownerId);
            if (owner.isPresent()){
                return owner.get();
        }
            throw new OwnerException("id not found");
    }


    @Override
    public Boolean deleteOwner(String id) throws OwnerException, NumberFormatException{
        if (id == null){
            throw new OwnerException("Invalid id");}
        Long ownerId = Long.parseLong(id);
        return ownerRepository.deleteById(ownerId);

    }
}
