package com.technikon.UI;

import com.technikon.jpa.JpaUtil;
import com.technikon.models.Property;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.PropertyRepairRepository;
import com.technikon.repositories.PropertyRepository;
import com.technikon.services.PropertyRepairService;
import com.technikon.services.PropertyRepairServiceImpl;
import com.technikon.services.PropertyService;
import com.technikon.services.PropertyServiceImpl;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminUI implements User {

    @Override
    public void showRepairs() {
        //initialize property reapairs repository and service
        PropertyRepairRepository rRep = new PropertyRepairRepository(JpaUtil.getEntityManager());
        PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(rRep);
        List<PropertyRepair> repairs = propertyRepairService.getPropertyRepairs();
        repairs.stream().forEach(System.out::println);
    }

    @Override
    public void getReport() {
        System.out.println("---------REPORT ON THE PROPERTY REPAIRS---------");
        showRepairs();
        System.out.println("------------------------------------------------");

    }

    @Override
    public void displayOnSelection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void UserMenu() {
        Scanner scanner = new Scanner(System.in);
        OUTER:
        do {
            System.out.println("""
                                           What would you like to do?
                                           1: Get Reports on the Repairs...
                                           2: Create Repair...
                                           3: Update Repair...
                                           4: Exit app...
                                           """);
            int choice = Integer.parseInt(scanner.next());
            switch (choice) {
                case 1: //REPORTS
                    getReport();
                    break;
                case 2: //create repairs
                    createNewRepair();
                    break;
                case 3: //delete property
                    updateRepair();
                    break;
                default:
                    break OUTER;
            }
        } while (true);
    }

    private void createNewRepair() {
        //initialize property repository and service
        PropertyRepository pRep = new PropertyRepository(JpaUtil.getEntityManager());
        PropertyService propertyService = new PropertyServiceImpl(pRep);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Submit the repair...");
        //get and find by E9 the property for which the repair will be submitted
        System.out.println("For which Property is the repair?");
        Optional<Property> propertyToRepair;
        do {
            String e9 = scanner.next();
            propertyToRepair = propertyService.findPropertyByE9(e9);
            if (propertyToRepair.isEmpty()) {
                System.out.println("Could not find the requested Property...");
                System.out.println("Please enter an existing E9 ............");
            }
        } while (propertyToRepair.isEmpty());   //untill repair found
        
        //initialize property reapairs repository and service
        PropertyRepairRepository rRep = new PropertyRepairRepository(JpaUtil.getEntityManager());
        PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(rRep);
        //send to UI to get repair details
        PropertyRepair repair = FrontEnd.createNewPropertyRepair(propertyToRepair.get(),propertyRepairService);
        propertyRepairService.savePropertyRepair(repair);
        System.out.println("REPAIR SUBMITTED SUCCESSFULLY!!!");
    }

    private void updateRepair() {
        //initialize property reapairs repository and service
        PropertyRepairRepository rRep = new PropertyRepairRepository(JpaUtil.getEntityManager());
        PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(rRep);
        //get Owner's unanswered repairs
        List<PropertyRepair> repairs = propertyRepairService.getPropertyRepairs();
        
        for (int i = 0; i < repairs.size(); i++) {
            System.out.println((i + 1) + ": " + repairs.get(i));
        }
        //get repair to answer
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Which repair would you like to update?");
            choice = Integer.parseInt(scanner.next());
        } while (choice < 1 || choice > repairs.size());
        
        choice--;//matching the list's indexes
        PropertyRepair repairToUpdate = repairs.get(choice);
        repairToUpdate = FrontEnd.updateRepair(repairToUpdate);  //call the fronted to communicate with the user and update the repair
        propertyRepairService.savePropertyRepair(repairToUpdate);//save the changes
    }

}
