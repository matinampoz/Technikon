package com.technikon.services;

import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.PropertyRepairRepository;
import enums.RepairStatus;
import enums.RepairType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PropertyRepairServiceImpl implements PropertyRepairService {

    private final PropertyRepairRepository propertyRepairRepository;

    public PropertyRepairServiceImpl(PropertyRepairRepository propertyRepairRepository) {
        this.propertyRepairRepository = propertyRepairRepository;
    }

    @Override
    public PropertyRepair createPropertyRepair(PropertyOwner owner, Property property, RepairType typeOfRepair,
            String shortDescription, String workDescription,
            LocalDateTime proposedStartDate, LocalDateTime proposedEndDate,
            double proposedCost) {

        PropertyRepair repair = PropertyRepair.builder()
                .owner(owner)
                .property(property)
                .typeOfRepair(typeOfRepair)
                .shortDescription(shortDescription)
                .workDescription(workDescription)
                .submissionDate(LocalDateTime.now())
                .proposedStartDate(proposedStartDate)
                .proposedEndDate(proposedEndDate)
                .proposedCost(proposedCost)
                .status(RepairStatus.PENDING)
                .build();
        return repair;
    }

    @Override
    public Long savePropertyRepair(PropertyRepair propertyRepair) {
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
    public List<PropertyRepair> searchPropertyRepairsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return propertyRepairRepository.findBySubmissionDateBetween(startDate, endDate);
    }

    @Override
    public List<PropertyRepair> searchPropertyRepairsByOwnerId(Long ownerId) {
        return propertyRepairRepository.findByOwner_Id(ownerId);
    }
}
