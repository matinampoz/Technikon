package com.technikon.models;

import com.technikon.enums.PropertyType;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public String toString() {
        return "Property{" + " Ε9=" + e9 + ", Αddress=" + address + ", Υear Of Construction=" + yearOfConstruction + ", Τype Of Property=" + typeOfProperty + '}';
    }
}
