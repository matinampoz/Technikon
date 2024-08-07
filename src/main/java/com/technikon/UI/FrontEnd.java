package com.technikon.UI;

import com.technikon.exceptions.OwnerException;
import com.technikon.jpa.JpaUtil;
import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.repositories.OwnerRepository;
import com.technikon.repositories.PropertyRepository;
import com.technikon.services.OwnerService;
import com.technikon.services.OwnerServiceImpl;
import com.technikon.services.PropertyService;
import com.technikon.services.PropertyServiceImpl;
import enums.PropertyType;
import java.util.List;
import java.util.Scanner;

public class FrontEnd {

    public static PropertyOwner logIn() throws OwnerException {
        //initialize owner repository and service
        OwnerRepository oRep = new OwnerRepository(JpaUtil.getEntityManager());
        OwnerService ownerService = new OwnerServiceImpl(oRep);

        List<PropertyOwner> owners = ownerService.getAllOwners();
        if (owners.size() == 0) {
            throw new OwnerException("There are no owners in the application. Please try entering one first.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the email address of the Property Owner you want to Log In as:");
        String logInEmail = scanner.next();

        return ownerService.searchOwnerByEmail(logInEmail);

    }

    /**
     * The static method createNewPropertyOwner is called to create a new
     * property owner and then calls the method createNewProperty to create the
     * owner's first two Properties.
     *
     * @return the PropertyOwner created.
     */
    public static PropertyOwner createNewPropertyOwner() {
        //initialize owner repository and service
        OwnerRepository oRep = new OwnerRepository(JpaUtil.getEntityManager());
        OwnerService ownerService = new OwnerServiceImpl(oRep);

        //get new owner's information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!!!");
        System.out.println("Please enter Property Owner's Name:");
        String name = scanner.next();
        System.out.println("Please enter Property Owner's Surname:");
        String surname = scanner.next();
        System.out.println("Please enter Property Owner's VAT:");
        String vat = scanner.next();
        System.out.println("Please enter Property Owner's Address:");
        String address = scanner.next();
        System.out.println("Please enter Property Owner's Phone Number:");
        Long phoneNumber = Long.parseLong(scanner.next());
        System.out.println("Please enter Property Owner's Email:");
        String email = scanner.next();
        System.out.println("Please enter a Username:");
        String username = scanner.next();
        System.out.println("Please enter the Password:");
        String password = scanner.next();

        //create the new owner with the given information
        PropertyOwner newOwner = ownerService.createOwner(vat, name, surname, address, 0, email, username, password);
        //create the owner's first 2 properties
        System.out.println("Now let's enter the Owner's first 2 Properties!");
        for (int i = 0; i < 2; i++) {
            System.out.println("Please enter the details of the Property " + (i + 1) + " :");
            createNewProperty(newOwner);
        }
        ownerService.saveOwner(newOwner);
        System.out.println("Property Owner " + newOwner.getName() + " " + newOwner.getSurname() + " CREATED!");
        return newOwner;
    }

    /**
     * The static method createNewProperty is called to create and save a new
     * property .
     */
    public static Property createNewProperty(PropertyOwner owner) {
        //initialize property repository and service
        PropertyRepository pRep = new PropertyRepository(JpaUtil.getEntityManager());
        PropertyService propertyService = new PropertyServiceImpl(pRep);

        //get the Property's information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!!!");
        System.out.println("Please enter Property's E9 Identification Number:");//E9
        String e9 = scanner.next();

        System.out.println("Is this property the address of the owner (Y/N)?");//ADDRESS
        String ans = scanner.next();
        String address;
        if (ans.toUpperCase().equals("Y") || ans.toUpperCase().equals("YES")) {
            address = owner.getAddress();
        } else {
            System.out.println("Please enter the Address of the Property:");
            address = scanner.next();
        }

        System.out.println("Please enter the Property's Year Of Construction:");//YEAR OF CONSTRUCTION
        int yearOfConstruction = Integer.parseInt(scanner.next());

        System.out.println("What type of Property is this?");//PROPERTY TYPE
        System.out.println("""
                           Select:
                           1: For Detached House...
                           2: For Maisonette...
                           3: For Apartment Building
                           """);
        int choice = Integer.parseInt(scanner.next());
        PropertyType type;
        type = switch (choice) {
            case 1 ->
                PropertyType.DETACHED_HOUSE;
            case 2 ->
                PropertyType.MAISONETTE;
            default ->
                PropertyType.APARTMENT_BUILDING;
        };
        //we have the OWNER from the parameters
        //create the Property
        Property newProperty = propertyService.createProperty(e9, address, yearOfConstruction, type, owner);
        //save the Property
        propertyService.saveProperty(newProperty);
        System.out.println("Property at address " + newProperty.getAddress() + " CREATED!");

        return newProperty;
    }

    public static Property updatProperty(Property property) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("This Property has:");
            System.out.println("1: Address = " + property.getAddress());
            System.out.println("2: Year Of Construction = " + property.getYearOfConstruction());
            System.out.println("3: Property Type = " + property.getTypeOfProperty());

            System.out.println("Please enter one of these numbers to update the field or any other number to finish the update...");
            choice = Integer.parseInt(scanner.next());

            switch (choice) {
                case 1:
                    System.out.println("Please enter the Address of the Property:");
                    String address = scanner.next();
                    property.setAddress(address);
                    System.out.println("Address updated...");
                    break;
                case 2:
                    System.out.println("Please enter the Year Of Construction of the Property:");
                    int yoc = Integer.parseInt(scanner.next());
                    property.setYearOfConstruction(yoc);
                    System.out.println("Year Of Construction updated...");
                    break;
                case 3:
                    System.out.println("What type of Property is this?");
                    System.out.println("""
                                          Select:
                                          1: For Detached House...
                                          2: For Maisonette...
                                          3: For Apartment Building
                                          """);
                    int pType = Integer.parseInt(scanner.next());
                    PropertyType type = switch (pType) {
                        case 1 ->
                            PropertyType.DETACHED_HOUSE;
                        case 2 ->
                            PropertyType.MAISONETTE;
                        default ->
                            PropertyType.APARTMENT_BUILDING;
                    };
                    property.setTypeOfProperty(type);
                    System.out.println("Property Type updated...");
                    break;
                default:
                    System.out.println("Saving changes......");
                    break;
            }
        } while (choice > 3 || choice < 1);

        return property;
    }
}
