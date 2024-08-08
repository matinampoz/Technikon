package com.technikon;

import com.technikon.enums.PropertyType;
import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.PropertyRepository;
import com.technikon.services.PropertyService;
import com.technikon.services.PropertyServiceImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PropertyServiceTest {

    @Mock
    private PropertyRepository propertyRepository;

    //testData
    PropertyOwner testOwner1;
    PropertyOwner testOwner2;
    List<Property> testProperties;

    @InjectMocks
    private PropertyService propertyService;

    @BeforeEach
    public void setUp() {
        // mockito initialization
        MockitoAnnotations.openMocks(this);
        propertyService = Mockito.mock(PropertyServiceImpl.class);

        testOwner1 = new PropertyOwner();
        testOwner1.setVatNumber("0123");
        testOwner2 = new PropertyOwner();
        testOwner2.setVatNumber("0124");
        testProperties = Arrays.asList(
                new Property(0L, "E99", "KP 33", 2006, PropertyType.MAISONETTE, testOwner1, new ArrayList<PropertyRepair>()),
                new Property(1L, "E98", "KP 34", 2010, PropertyType.APARTMENT_BUILDING, testOwner1, new ArrayList<PropertyRepair>()),
                new Property(2L, "E97", "KP 35", 1970, PropertyType.DETACHED_HOUSE, testOwner2, new ArrayList<PropertyRepair>())
        );
    }

    @Test
    public void testCreateProperty() {
        Property expectedProperty = new Property();
        expectedProperty.builder()
                .e9("E100")
                .address("KP 30")
                .yearOfConstruction(2004)
                .typeOfProperty(PropertyType.DETACHED_HOUSE)
                .propertyOwner(testOwner2)
                .build();

        Property result = propertyService.createProperty("E100", "KP 30", 2004, PropertyType.DETACHED_HOUSE, testOwner2);
        assertEquals(expectedProperty, result);

    }

//    @Test
//    public void testSaveProperty() throws PropertyException {
//        Optional<Property> savedProperty = Optional.of(testProperties.get(0));
//        when(propertyRepository.save(testProperties.get(0))).thenReturn(savedProperty);
//
//        String result = propertyService.saveProperty(testProperties.get(0));
//        assertEquals(testProperties.get(0).getE9(), result);
//    }
//
//    @Test
//    public void testGetAllProperties() {
//        when(propertyRepository.findAll()).thenReturn(testProperties);
//
//        List<Property> result = propertyService.getAllProperties();
//        assertEquals(testProperties.size(), result.size());
//    }
//
//    @Test
//    public void testFindPropertiesByVAT() {
//        when(propertyRepository.findAll()).thenReturn(testProperties);
//
//        //testing for the first owner
//        List<Property> result = propertyService.findPropertiesByVAT(0123L);
//        assertEquals(2, result.size());
//
//        //testing for the second owner
//        List<Property> result2 = propertyService.findPropertiesByVAT(0124L);
//        assertEquals(1, result2.size());
//    }
//
//    @Test
//    public void testFindPropertyByID() {
//        Property mockProperty = testProperties.get(0);
//        when(propertyRepository.findById(mockProperty.getPropertyId())).thenReturn(Optional.of(mockProperty));
//        
//        Optional<Property> result = propertyService.findPropertyByID(testProperties.get(0).getPropertyId());
//        assertEquals(mockProperty, result.get());
//        
//    }
//
//    @Test
//    public void testFindPropertyByE9() {
//        Property mockProperty = testProperties.get(0);
//        when(propertyRepository.findById(mockProperty.getE9())).thenReturn(Optional.of(mockProperty));
//        
//        Optional<Property> result = propertyService.findPropertyByID(testProperties.get(0).getPropertyId());
//        assertEquals(mockProperty, result.get());
//    }
}
