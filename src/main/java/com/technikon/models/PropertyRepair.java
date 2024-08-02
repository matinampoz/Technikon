package com.technikon.models;

import enums.RepairStatus;
import enums.RepairType;
import java.time.LocalDateTime;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PropertyRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long repairId;
    
    @ManyToOne
    @JoinColumn(name = "vatNumber", referencedColumnName = "vatNumber")
    private PropertyOwner owner;
    
    @ManyToOne
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    private Property property;
    
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
