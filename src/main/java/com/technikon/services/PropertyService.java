package com.technikon.services;

import com.technikon.models.Property;
import java.util.List;
import java.util.Optional;

public interface PropertyService {
    
    /**
     * The method createProperty calls the User Interface that gets the
     * information for the new property and returns the created property
     */
    Property createProperty();
    
    /**
     * The method saveProperty gets a Property object, saves it to the database
     * and returns its E9
     */
    String saveProperty(Property property);
    
    /**
     * The method updateProperty gets a Property object, calls the UI to update
     * its values and returns it updated
     */
    Property updateProperty(Property propertyToUpdate);
    
    /**
     * The method deleteProperty gets a property's id and calls the property
     * repository to delete the property with that id from the database if it
     * exists. Returns true if the property was found and deleted and false if
     * it couldn't delete a property with that id.
     */
    boolean deleteProperty(Long id);
    
    /**
     * The method findPropertiesByVAT gets a Property owner's VAT and returns
     * all the properties that the owner has
     */
    List<Property> findPropertiesByVAT(String ownerVat);
    
    /**
     * The method findPropertyByID gets the id of the property that is searched
     * and returns an Optional<Property> which contains the property if the
     * property with the given id exists. If the given ID is null throws
     * InvalidParameterException.
     */
    Optional<Property> findPropertyByID(Long id);
    
    /**
     * The method findPropertyByE9 gets the E9 String of the property that is
     * searched and returns an Optional<Property> which contains the property if
     * the property with the given E9 exists. If the given E9 is null throws
     * InvalidParameterException.
     */
    Optional<Property> findPropertyByE9(String e9);
}
