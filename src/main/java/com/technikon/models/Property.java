package com.technikon.models;

import enums.PropertyType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "property")
public class Property {
    @Id
    private long propertyID;
    private String address;
    private int yearOfConstruction;
    private PropertyType typeOfProperty;
    @ManyToOne
    @JoinColumn(name = "VATnumber", referencedColumnName = "VATnumber")
    private long ownerVAT;
}
