package com.technikon.models;

import enums.RepairStatus;
import enums.RepairType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRepair {

    private int repairId;
    private int ownerId;
    private int propertyId;
    private RepairType typeOfRepair;
    private String shortDescription;
    private LocalDateTime submissionDate;
    private String workDescription;
    private LocalDateTime proposedStartDate;
    private LocalDateTime proposedEndDate;
    private double proposedCost;
    private boolean ownerAcceptance;
    private RepairStatus status;
    private LocalDateTime actualStartDate;
    private LocalDateTime actualEndDate;

}
