package com.technikon.models;

import enums.PropertyType;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Property {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long propertyId;
    
    @Column(unique = true, nullable = false)
    private String e9;
    
    private String address;
    
    private int yearOfConstruction;
    
    private PropertyType typeOfProperty;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ownerId")
    private PropertyOwner propertyOwner;
    
    @OneToMany(mappedBy = "property")
    private List<PropertyRepair> repairs;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Property other = (Property) obj;
        if (this.propertyId != other.propertyId) {
            return false;
        }
        if (this.yearOfConstruction != other.yearOfConstruction) {
            return false;
        }
        if (!Objects.equals(this.e9, other.e9)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (this.typeOfProperty != other.typeOfProperty) {
            return false;
        }
        if (!Objects.equals(this.propertyOwner, other.propertyOwner)) {
            return false;
        }
        return Objects.equals(this.repairs, other.repairs);
    }
    
    
    
}
