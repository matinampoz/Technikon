package com.technikon.UI;

import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import java.util.List;

public interface User {
    
    PropertyOwner createNewOwner();
    Property createNewProperty();
    PropertyRepair createNewPropertyRepair();
    List<Property> getProperties();
    List<PropertyRepair> getRepairs();
    int getAction();
    
}
