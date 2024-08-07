package com.technikon.services;

import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import enums.RepairType;
import java.time.LocalDateTime;
import java.util.List;

public interface PropertyRepairService {

    PropertyRepair createPropertyRepair(Property property, RepairType typeOfRepair,
            String shortDescription, String workDescription, String submissionDate,
            String proposedStartDate, String proposedEndDate,
            double proposedCost);

    Long savePropertyRepair(PropertyRepair propertyRepair);

    List<PropertyRepair> getPropertyRepairs();

    List<PropertyRepair> getOwnerRepairs(String ownerVAT);

    List<PropertyRepair> getUnansweredOwnerRepairs(String ownerVAT);

    PropertyRepair updatePropertyRepair(PropertyRepair propertyRepair);

    void deletePropertyRepair(Long id);

    List<PropertyRepair> searchPropertyRepairsByDateRange(String startDate, String endDate);

    List<PropertyRepair> searchPropertyRepairsByOwnerId(Long ownerId);
}
