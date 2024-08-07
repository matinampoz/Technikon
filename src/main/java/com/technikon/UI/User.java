package com.technikon.UI;

import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import java.util.List;

public interface User {

    Property addNewProperty();
    
    void deleteProperty();

    void showProperties();

    void showRepairs();

    void displayOnSelection();

    void getReport();
    
    void UserMenu();

}
