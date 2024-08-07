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
    @JoinColumn(name = "propertyId", referencedColumnName = "propertyId")
    private Property property;

    private RepairType typeOfRepair;
    private String shortDescription;
    private String submissionDate;
    private String workDescription;
    private String proposedStartDate;
    private String proposedEndDate;
    private double proposedCost;
    private boolean ownerAcceptance;
    private RepairStatus status;
    private String actualStartDate;
    private String actualEndDate;

}
