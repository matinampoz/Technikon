package com.technikon.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PropertyOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long vatNumber;
    private String name;
    private String surname;
    private String address;
    private long phoneNumber;
    private String email;
    private String username;
    private String password;

    @OneToMany(mappedBy = "propertyOwner")
    List<Property> properties;
}
