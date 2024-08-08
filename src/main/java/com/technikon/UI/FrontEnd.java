package com.technikon.UI;

import com.technikon.exceptions.OwnerException;
import com.technikon.exceptions.PropertyException;
import com.technikon.jpa.JpaUtil;
import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.OwnerRepository;
import com.technikon.repositories.PropertyRepository;
import com.technikon.services.OwnerService;
import com.technikon.services.OwnerServiceImpl;
import com.technikon.services.PropertyRepairService;
import com.technikon.services.PropertyService;
import com.technikon.services.PropertyServiceImpl;
import enums.PropertyType;
import enums.RepairStatus;
import enums.RepairType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        PropertyOwner newOwner = ownerService.createOwner(vat, name, surname, address, phoneNumber, email, username, password);
        //create the owner's first 2 properties
        System.out.println("Now let's enter the Owner's first 2 Properties!");
        for (int i = 0; i < 2; i++) {
            System.out.println("Please enter the details of the Property " + (i + 1) + " :");
            try {
                createNewProperty(newOwner);
            } catch (PropertyException ex) {
                System.out.println(ex.getMessage());
            }
        }
        ownerService.saveOwner(newOwner);
        System.out.println("Property Owner " + newOwner.getName() + " " + newOwner.getSurname() + " CREATED!");
        return newOwner;
    }

    /**
     * The static method createNewProperty is called to create and save a new
     * property .
     */
    public static Property createNewProperty(PropertyOwner owner) throws PropertyException {
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

        System.out.println("Please enter the Address of the Property:");
        String address = scanner.next();

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

    public static PropertyRepair createNewPropertyRepair(Property property, PropertyRepairService propertyRepairService) {

        //get new repair's details
        Scanner scanner = new Scanner(System.in);
        System.out.println("What type of Repair is this?");
        System.out.println("""
                                          Select:
                                            1: For Painting...
                                            2: For Insulation...
                                            3: For Frames...
                                            4: For Plumping...
                                            5: For Electrical Work...
                                          """);
        int choice = Integer.parseInt(scanner.next());
        RepairType repairType = switch (choice) {
            case 1 ->
                RepairType.PAINTING;
            case 2 ->
                RepairType.INSULATION;
            case 3 ->
                RepairType.FRAMES;
            case 4 ->
                RepairType.PLUMPING;
            default ->
                RepairType.ELECTRICAL_WORK;
        };
        String submissionDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString();
        System.out.println("Please enter a short description of the Repair:");
        String shortDescription = scanner.next();
        System.out.println("Please enter the work description of the Repair:");
        String workDescription = scanner.next();
        System.out.println("Please enter the Proposed Start Date for the Repair:");
        String proposedStartDate = scanner.next();
        System.out.println("Please enter the Proposed End Date for the Repair:");
        String proposedEndDate = scanner.next();
        System.out.println("Please enter the Prospected Cost of the Repair:");
        double proposedCost = Double.parseDouble(scanner.next());

        return propertyRepairService.createPropertyRepair(property, repairType, submissionDate, shortDescription, workDescription, proposedStartDate, proposedEndDate, proposedCost, false);
    }

    static PropertyRepair updateRepair(PropertyRepair repair) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Repair Details:");

            System.out.println("1: Repair Type = " + repair.getTypeOfRepair());
            System.out.println("2: Repair Description = " + repair.getShortDescription());
            System.out.println("3: Work Description = " + repair.getWorkDescription());
            if (repair.getStatus().equals(RepairStatus.PENDING) || repair.getStatus().equals(RepairStatus.DECLINED)) {
                System.out.println("4: Start Date = " + repair.getProposedStartDate());
                System.out.println("5: End Date = " + repair.getProposedEndDate());
            } else {
                System.out.println("4: Start Date = " + repair.getActualStartDate());
                System.out.println("5: End Date = " + repair.getActualEndDate());
            }
            System.out.println("6: Cost = " + repair.getProposedCost());
            System.out.println("7: Status = " + repair.getStatus());

            System.out.println("Please enter one of these numbers to update the field or any other number to finish the update...");
            choice = Integer.parseInt(scanner.next());

            //do the update
            switch (choice) {
                case 1:
                    System.out.println("""
                                          Select:
                                            1: For Painting...
                                            2: For Insulation...
                                            3: For Frames...
                                            4: For Plumping...
                                            5: For Electrical Work...
                                          """);
                    int rType = Integer.parseInt(scanner.next());
                    RepairType repairType = switch (rType) {
                        case 1 ->
                            RepairType.PAINTING;
                        case 2 ->
                            RepairType.INSULATION;
                        case 3 ->
                            RepairType.FRAMES;
                        case 4 ->
                            RepairType.PLUMPING;
                        default ->
                            RepairType.ELECTRICAL_WORK;
                    };
                    repair.setTypeOfRepair(repairType);
                    System.out.println("Repair Type updated...");
                    break;
                case 2:
                    System.out.println("Please enter a new Repair Short Description:");
                    String shortDescription = scanner.next();
                    repair.setShortDescription(shortDescription);
                    System.out.println("Short Description updated...");
                    break;
                case 3:
                    System.out.println("Please enter a new Repair Work Description:");
                    String workDescription = scanner.next();
                    repair.setWorkDescription(workDescription);
                    System.out.println("Work Description updated...");
                    break;
                case 4:
                    if (repair.getStatus().equals(RepairStatus.PENDING) || repair.getStatus().equals(RepairStatus.DECLINED)) {
                        System.out.println("Please propose a new Starting Date:");
                        String psd = scanner.next();
                        repair.setProposedStartDate(psd);
                        if (repair.getStatus().equals(RepairStatus.DECLINED)) {
                            repair.setStatus(RepairStatus.PENDING);
                        }
                    } else {
                        System.out.println("Please a new Starting Date:");
                        String asd = scanner.next();
                        repair.setActualStartDate(asd);
                    }
                    System.out.println("Starting Date updated...");
                    break;
                case 5:
                    if (repair.getStatus().equals(RepairStatus.PENDING) || repair.getStatus().equals(RepairStatus.DECLINED)) {
                        System.out.println("Please propose a new End Date:");
                        String ped = scanner.next();
                        repair.setProposedEndDate(ped);
                        if (repair.getStatus().equals(RepairStatus.DECLINED)) {
                            repair.setStatus(RepairStatus.PENDING);
                        }
                    } else {
                        System.out.println("Please a new End Date:");
                        String aed = scanner.next();
                        repair.setActualEndDate(aed);
                    }
                    System.out.println("End Date updated...");
                    break;
                case 6:
                    System.out.println("Please enter the new Proposed Cost");
                    double cost = Double.parseDouble(scanner.next());
                    repair.setProposedCost(cost);
                    if (repair.getStatus().equals(RepairStatus.DECLINED)) {
                        repair.setStatus(RepairStatus.PENDING);
                    }
                    System.out.println("Cost updated...");
                    break;
                case 7:
                    System.out.println("Is the repair complete?(Y/N)");
                    String ans = scanner.next();
                    if (ans.toUpperCase().equals("Y") || ans.toUpperCase().equals("YES")) {
                        repair.setStatus(RepairStatus.COMPLETE);
                        System.out.println("Repair marked as Complete...");
                    }
                    break;
                default:
                    System.out.println("Saving changes......");
                    break;
            }
        } while (choice > 7 || choice < 1);

        return repair;
    }
}
