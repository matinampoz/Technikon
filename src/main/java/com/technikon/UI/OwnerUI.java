package com.technikon.UI;

import com.technikon.exceptions.OwnerException;
import com.technikon.exceptions.PropertyException;
import com.technikon.jpa.JpaUtil;
import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.PropertyRepairRepository;
import com.technikon.repositories.PropertyRepository;
import com.technikon.services.PropertyRepairService;
import com.technikon.services.PropertyRepairServiceImpl;
import com.technikon.services.PropertyService;
import com.technikon.services.PropertyServiceImpl;
import enums.RepairStatus;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OwnerUI implements User {

    private PropertyOwner owner;

    //Constructor
    public OwnerUI() {
        this.owner = new PropertyOwner();
    }

    //Setter
    public void setOwner(PropertyOwner owner) {
        this.owner = owner;
    }

    /**
     * The method addnewProperty is called when the Owner want's to create a new
     * Property.
     *
     * @return the Property created.
     */
    public Optional<Property> addNewProperty() {
        try {
            return Optional.of(FrontEnd.createNewProperty(owner));
        } catch (PropertyException ex) {
            System.out.println(ex.getMessage());
            return Optional.empty();
        }
    }

    /**
     * The method deleteProperty is called when the Owner wants to delete one
     * Property. Communicates with the User to find the property to delete and
     * informs them if the deletion was successfull.
     */
    public void deleteProperty() {
        //initialize property repository and service
        PropertyRepository pRep = new PropertyRepository(JpaUtil.getEntityManager());
        PropertyService propertyService = new PropertyServiceImpl(pRep);

        //get property to delete
        Scanner scanner = new Scanner(System.in);
        String e9;
        Optional<Property> propertyFound;
        do {
            System.out.println("Please enter the E9 of the Property you want to delete:");
            e9 = scanner.next();
            propertyFound = propertyService.findPropertyByE9(e9);
            if (propertyFound.isEmpty()) {
                System.out.println("Property with E9: (" + e9 + ") NOT FOUND! Please try again...");
            }
        } while (propertyFound.isEmpty());

        //delete the property
        boolean propertyDeleted = propertyService.deleteProperty(propertyFound.get().getPropertyId());
        //inform about the outcome of the deletion
        if (propertyDeleted) {
            System.out.println("PROPERTY DELETED SUCCESSFULLY!!!");
        } else {
            System.out.println("THE PROPERTY COULD NOT BE DELETED...");
        }
    }

    /**
     * This method reports on the Properties Linked to the Owner
     */
    public void showProperties() {
        //initialize property repository and service
        PropertyRepository pRep = new PropertyRepository(JpaUtil.getEntityManager());
        PropertyService propertyService = new PropertyServiceImpl(pRep);
        List<Property> properties = propertyService.findPropertiesByVAT(owner.getVatNumber());
        properties.stream().forEach(System.out::println);
    }

    /**
     * This method reports on the Repairs Linked to the Owner
     */
    @Override
    public void showRepairs() {
        //initialize property reapairs repository and service
        PropertyRepairRepository rRep = new PropertyRepairRepository(JpaUtil.getEntityManager());
        PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(rRep);
        List<PropertyRepair> repairs = propertyRepairService.getOwnerRepairs(owner.getVatNumber());
        repairs.stream().forEach(System.out::println);
        if (repairs.size() == 0) {
            System.out.println("NO REPAIRS REGISTERED!!!");
        }
    }

    /**
     * The method displayOnSelection should be displayed when the user has
     * connected. For the Owner it displays the Owner's Properties and the
     * Repairs linked to those Properties.
     */
    @Override
    public void displayOnSelection() {
        System.out.println("Hello " + this.owner.getName() + "!!!");
        System.out.println("Here are your properties and the repairs for each of them:");
        //initialize property repository and service
        PropertyRepository pRep = new PropertyRepository(JpaUtil.getEntityManager());
        PropertyService propertyService = new PropertyServiceImpl(pRep);
        List<Property> myProperties = propertyService.findPropertiesByVAT(owner.getVatNumber());
        for (Property property : myProperties) {
            System.out.println(property);
            System.out.println("Repairs for property (E9: " + property.getE9() + " ):");
            List<PropertyRepair> repairs = property.getRepairs();
            repairs.stream().forEach(System.out::println);
        }

    }

    /**
     * Communicates with the user to show him the report that he needs.
     */
    @Override
    public void getReport() {
        //ask the user which report they want to get
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                           What would you like to see?
                           
                           1) Display all my Properties.
                           2) Display all Repairs' Statuses.
                           
                           """);
        int choice = Integer.parseInt(scanner.next());  //get answer
        if (choice == 1) {   //if he asked for the properties
            showProperties();
        } else {              //if he inputed anything else
            showRepairs();
        }
    }

    /**
     * The method UserMenu is the method that navigates the users in all
     * available actions they can take.
     */
    @Override
    public void UserMenu() {
        Scanner scanner = new Scanner(System.in);
        OUTER:
        do {
            System.out.println("""
                                           What would you like to do?
                                           1: Get Reports on your Properties...
                                           2: Update a Property...
                                           3: Delete a Property...
                                           4: See submitted Repairs...
                                           5: Add a Property...
                                           6: Exit app...
                                           """);
            int choice = Integer.parseInt(scanner.next());
            switch (choice) {
                case 1: //REPORTS
                    getReport();
                    break;
                case 2: //update property
                    updatePropertyDetails();
                    break;
                case 3: //delete property
                    deleteProperty();
                    break;
                case 4: //see repairs
                    getUnansweredRepairs();
                    break;
                case 5: //New Property
                    addNewProperty();
                    break;
                default:
                    break OUTER;
            }
        } while (true);
    }

    /**
     * Communicates with the user to help them connect to the app
     *
     * @return the Signed In user-PropertyOwner
     */
    public PropertyOwner signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                           What would you like to do?
                           1) Create a new profile as a Property Owner.
                           2) Log In as an already existing Property Owner.
                           """);
        PropertyOwner owner;
        do {
            int choice = Integer.parseInt(scanner.next());
            if (choice == 1) {      //if the user wants to Sign Up
                owner = FrontEnd.createNewPropertyOwner();
            } else {                //if the user wants to Log In
                try {
                    owner = FrontEnd.logIn();
                } catch (OwnerException ex) {
                    System.out.println(ex.getMessage());
                    owner = null;
                }
            }
        } while (owner == null); //continue untill we have a user signed in

        return owner;
    }

    /**
     * The method updatePropertyDetails is called when the User wants to update
     * the fields of one Property. It gets the Property to be updated and sends
     * it to the Front End to get changes on it from the User. When that's over
     * the Property is saved to be updated.
     */
    private void updatePropertyDetails() {
        //initialize property repository and service
        PropertyRepository pRep = new PropertyRepository(JpaUtil.getEntityManager());
        PropertyService propertyService = new PropertyServiceImpl(pRep);

        //get property to update
        Scanner scanner = new Scanner(System.in);
        String e9;
        Optional<Property> propertyFound;
        do {
            System.out.println("Please enter the E9 of the Property you want to edit:");
            e9 = scanner.next();
            propertyFound = propertyService.findPropertyByE9(e9);
            if (propertyFound.isEmpty()) {
                System.out.println("Property with E9: (" + e9 + ") NOT FOUND! Please try again...");
            }
        } while (propertyFound.isEmpty());

        try {
            //save the new property after sending it to front end to get it updated
            propertyService.saveProperty(FrontEnd.updatProperty(propertyFound.get()));
            System.out.println("UPDATED PROPERTY SUCCESSFULLY!!!");
        } catch (PropertyException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void getUnansweredRepairs() {
        //initialize property reapairs repository and service
        PropertyRepairRepository rRep = new PropertyRepairRepository(JpaUtil.getEntityManager());
        PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(rRep);
        //get Owner's unanswered repairs
        List<PropertyRepair> repairs = propertyRepairService.getUnansweredOwnerRepairs(owner.getVatNumber());

        if (repairs.size() == 0) {
            System.out.println("NO REPAIRS REGISTERED!");
        } else {
            for (int i = 0; i < repairs.size(); i++) {
                System.out.println((i + 1) + ": " + repairs.get(i));
            }
            //get repair to answer
            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.println("Which repair would you like to verify?(Enter one of the numbers above...)");
                choice = Integer.parseInt(scanner.next());
            } while (choice < 1 || choice > repairs.size());

            choice--;//matching the list's indexes
            PropertyRepair repairToAnswer = repairs.get(choice);

            System.out.println("You chose to answer: " + repairToAnswer);
            System.out.println("Do you ACCEPT(A) or DECLINE(D) the repair?");
            String ans = scanner.next();
            if (ans.toUpperCase().equals("A") || ans.toUpperCase().equals("ACCEPT")) {
                System.out.println("REPAIR ACCEPTED!!!");
                repairToAnswer.setOwnerAcceptance(true);
                repairToAnswer.setStatus(RepairStatus.IN_PROGRESS);
                repairToAnswer.setActualStartDate(repairToAnswer.getProposedStartDate());
                repairToAnswer.setActualEndDate(repairToAnswer.getProposedEndDate());
            } else {
                System.out.println("REPAIR DECLINED...");
                repairToAnswer.setOwnerAcceptance(false);
                repairToAnswer.setStatus(RepairStatus.DECLINED);
            }
        }

    }

}
