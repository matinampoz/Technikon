package com.technikon.services;

import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import enums.RepairType;
import java.time.LocalDateTime;
import java.util.List;

public interface PropertyRepairService {

    PropertyRepair createPropertyRepair(PropertyOwner owner, Property property, RepairType typeOfRepair,
            String shortDescription, String workDescription,
            LocalDateTime proposedStartDate, LocalDateTime proposedEndDate,
            double proposedCost);

    Long savePropertyRepair(PropertyRepair propertyRepair);

    List<PropertyRepair> getPropertyRepairs();

    PropertyRepair updatePropertyRepair(PropertyRepair propertyRepair);

    void deletePropertyRepair(Long id);

    List<PropertyRepair> searchPropertyRepairsByDateRange(LocalDateTime startDate, LocalDateTime endDate);

    List<PropertyRepair> searchPropertyRepairsByOwnerId(Long ownerId);
}
