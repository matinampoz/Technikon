package com.technikon.models;

import enums.PropertyType;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "property")
public class Property {
    @Id
    private long propertyId;
    
    private String address;
    
    private int yearOfConstruction;
    
    private PropertyType typeOfProperty;
    
    @ManyToOne
    @JoinColumn(name = "VATnumber", referencedColumnName = "VATnumber")
    private PropertyOwner propertyOwner;
    
    @OneToMany(mappedBy = "property")
    private List<PropertyRepair> repairs;
}
