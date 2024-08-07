package com.technikon.services;

import com.technikon.models.Property;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.PropertyRepairRepository;
import enums.RepairStatus;
import enums.RepairType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PropertyRepairServiceImpl implements PropertyRepairService {

    private final PropertyRepairRepository propertyRepairRepository;

    public PropertyRepairServiceImpl(PropertyRepairRepository propertyRepairRepository) {
        this.propertyRepairRepository = propertyRepairRepository;
    }

    @Override
    public PropertyRepair createPropertyRepair(Property property, RepairType typeOfRepair, String submissionDate,
            String shortDescription, String workDescription,
            String proposedStartDate, String proposedEndDate,
            double proposedCost) {

        PropertyRepair repair = PropertyRepair.builder()
                .property(property)
                .typeOfRepair(typeOfRepair)
                .shortDescription(shortDescription)
                .workDescription(workDescription)
                .submissionDate(submissionDate)
                .proposedStartDate(proposedStartDate)
                .proposedEndDate(proposedEndDate)
                .proposedCost(proposedCost)
                .status(RepairStatus.PENDING)
                .build();
        return repair;
    }

    @Override
    public Long savePropertyRepair(PropertyRepair propertyRepair) {
        System.out.println("----------TRYING TO SAVE. savePropertyRepair() called");
        propertyRepairRepository.save(propertyRepair);
        return propertyRepair.getRepairId();
    }

    @Override
    public List<PropertyRepair> getPropertyRepairs() {
        return propertyRepairRepository.findAll();
    }

    @Override
    public PropertyRepair updatePropertyRepair(PropertyRepair propertyRepair) {
        Optional<PropertyRepair> optionalRepair = propertyRepairRepository.save(propertyRepair);
        if (optionalRepair.isPresent()) {
            return optionalRepair.get();
        } else {
            throw new RuntimeException("Failed to update PropertyRepair with id:" + propertyRepair.getRepairId());
        }
    }

    @Override
    public void deletePropertyRepair(Long id) {
        propertyRepairRepository.deleteById(id);
    }

    @Override
    public List<PropertyRepair> searchPropertyRepairsByDateRange(String startDate, String endDate) {
        return propertyRepairRepository.findBySubmissionDateBetween(startDate, endDate);
    }

    @Override
    public List<PropertyRepair> searchPropertyRepairsByOwnerId(Long ownerId) {
        return propertyRepairRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<PropertyRepair> getOwnerRepairs(String ownerVAT) {
        return getPropertyRepairs().stream()
                .filter(repair -> repair.getProperty().getPropertyOwner().getVatNumber().equals(ownerVAT))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyRepair> getUnansweredOwnerRepairs(String ownerVAT) {
        return getPropertyRepairs().stream()
                .filter(repair -> repair.getProperty().getPropertyOwner().getVatNumber().equals(ownerVAT))
                .filter(repair -> !repair.isOwnerAcceptance())
                .collect(Collectors.toList());
    }
}
