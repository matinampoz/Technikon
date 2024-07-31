package com.technikon.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "property_owner")
public class PropertyOwner {
    @Id
    private long vatNumber;
    private String name;
    private String surname;
    private String address;
    private long phoneNumber;
    private String email;
    private String username;
    private String password;
}
