package com.technikon.UI;

import com.technikon.models.Property;
import java.util.Optional;

public interface User {

    Optional<Property> addNewProperty();
    
    void deleteProperty();

    void showProperties();

    void showRepairs();

    void displayOnSelection();

    void getReport();
    
    void UserMenu();

}
