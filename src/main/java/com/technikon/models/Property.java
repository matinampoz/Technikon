package com.technikon.models;

import enums.PropertyType;
import javax.persistence.Column;
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
    
    @Column(name = "address", nullable = false)
    private String address;
    
    @Column(name = "yearOfConstruction", nullable = false)
    private int yearOfConstruction;
    
    @Column(name = "propertyType", nullable = false)
    private PropertyType typeOfProperty;
    
    @ManyToOne
    @JoinColumn(name = "VATnumber", referencedColumnName = "VATnumber")
    private PropertyOwner propertyOwner;
}
