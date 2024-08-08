package com.technikon.models;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity

/**
 * The PropertyOwner class represents an owner of one or more properties.
 * It contains personal and contact information for the owner, as well as a list of properties owned.
 *
 * @author matina
 */
public class PropertyOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ownerId;

    @Column(unique = true)
    private String vatNumber;
    private String name;
    private String surname;
    private String address;
    private long phoneNumber;
    private String email;
    private String username;
    private String password;

    /**
     * A list of properties owned by the PropertyOwner.
     * This is a one-to-many relationship, where each property is associated with one owner.
     */
    @OneToMany(mappedBy = "propertyOwner")
    List<Property> properties;

}
