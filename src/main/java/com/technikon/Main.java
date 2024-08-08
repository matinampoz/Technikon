package com.technikon;

import com.technikon.UI.AdminUI;
import com.technikon.UI.OwnerUI;
import com.technikon.UI.User;
import com.technikon.exceptions.PropertyException;
import com.technikon.jpa.JpaUtil;
import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.OwnerRepository;
import com.technikon.repositories.PropertyRepairRepository;
import com.technikon.repositories.PropertyRepository;
import com.technikon.services.OwnerServiceImpl;
import com.technikon.services.PropertyRepairServiceImpl;
import com.technikon.services.PropertyServiceImpl;
import com.technikon.utility.OwnerParser;
import enums.PropertyType;
import enums.RepairType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {
        // Initialize JPA

        EntityManager em = JpaUtil.getEntityManager();
        // Create Repository and Service instances
        OwnerRepository ownerRepository = new OwnerRepository(em);
        OwnerServiceImpl ownerService = new OwnerServiceImpl(ownerRepository);
        PropertyRepository propertyRepository = new PropertyRepository(em);
        PropertyServiceImpl propertyService = new PropertyServiceImpl(propertyRepository);
        PropertyRepairRepository propertyRepairRepository = new PropertyRepairRepository(em);
        PropertyRepairServiceImpl propertyRepairService = new PropertyRepairServiceImpl(propertyRepairRepository);

        
        OwnerParser parser = new OwnerParser();
        String fileName="src/main/resources/OwnerData.csv";
        parser.loadOwners(fileName);
        
        
//        System.out.println("------------------------------------------------");
//
//        System.out.println("------TRYING TO CREATE OWNER----------------------------");
//        PropertyOwner owner = ownerService.createOwner(
//                "123456789",
//                "John",
//                "Doe",
//                "123 Elm Street",
//                5551234L,
//                "john.doe@example.com",
//                "johnny",
//                "securepassword");
//        System.out.println("--------------OWNER CREATED----------------");
//
//        System.out.println("------TRYING TO SAVE OWNER----------------------------");
//        ownerService.saveOwner(owner);
//        System.out.println("--------------OWNER SAVED----------------");
//
////        System.out.println("---------------SEARCH BY EMAIL OWNER------------------");
////        System.out.println(ownerService.searchOwnerByEmail("john.doe@example.com"));
////        System.out.println("---------------SEARCH BY EMAIL RESULT---------------");
//////
////        System.out.println("-----------------SEARCH BY VAT OWNER ------------");
////        System.out.println(ownerService.searchOwnerByVat("123456789"));
////        System.out.println("-----------------SEARCH BY VAT RESULT ------------");
////        System.out.println("-----------------SEARCH BY ID OWNER ------------");
////        System.out.println(ownerService.searchOwnerById("1"));
////        System.out.println("-----------------SEARCH BY ID RESULT ------------");
////
////        System.out.println("-----------------DELETE OWNER ------------");
////        ownerService.deleteOwner("1");
////        System.out.println("-----------------DELETED OWNER ------------");
////
//        System.out.println("--------ONTO PROPERTY CREATION----------");
//        Property property = propertyService.createProperty("A001",
//                "123 Elm Street",
//                1990,
//                PropertyType.MAISONETTE,
//                owner);
//        System.out.println("------------------------------------------------");
//        System.out.println("--------------PROPERTY CREATED----------------");
//        try {
//            propertyService.saveProperty(property);
//        } catch (PropertyException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        System.out.println("----------------PROPERTY SAVED-----------------");
//
////        System.out.println("--------------DELETE BY ID PROPERTY--------");
////        propertyService.deleteProperty(1L);
////        System.out.println("---------------DELETE BY ID RESULT-----------");
////        System.out.println("--------------FIND PROPERTY BY VAT PROPERTY-------------");
////        List<Property> allProperty= propertyService.findPropertiesByVAT(ownerVat);
////        System.out.println(allProperty);
////        System.out.println("------------------FIND PROPERTY BY VAT RESULT----------------");
////        System.out.println("--------------FIND PROPERTY BY ID PROPERTY-------------");
////        System.out.println(propertyService.findPropertyByID(1L));
////        System.out.println("--------------FIND PROPERTY BY ID RESULT-------------");
////        System.out.println("--------------FIND PROPERTY BY E9 PROPERTY-------------");
////        System.out.println(propertyService.findPropertyByE9("A001"));
////        System.out.println("--------------FIND PROPERTY BY E9 RESULT-------------");
//        System.out.println("-------------ONTO PROPERTY REPAIR CREATION");
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String timestamp = now.format(formatter);
//        PropertyRepair repair = propertyRepairService.createPropertyRepair(
//                property,
//                RepairType.FRAMES,
//                timestamp,
//                "Wdesc",
//                "12/5/98",
//                "Aurio",
//                "Paraskeuh",
//                1000,
//                false);
//
//        propertyRepairService.savePropertyRepair(repair);
//        System.out.println("----------------REPAIR CREATED AND SAVED-----------------------------");
//
////        System.out.println("------------------------ALL REPAIRS----------------------------------------");
////        List<PropertyRepair> allPropertyRepairs = propertyRepairService.getPropertyRepairs();
////        System.out.println(allPropertyRepairs);
////        System.out.println("------------------------ ALL REPAIRS RESULT------------------");
////        System.out.println("----------UPDATE REPAIR----------");
////        repair.setTypeOfRepair(RepairType.PAINTING);
////        PropertyRepair updatedRepair = propertyRepairService.updatePropertyRepair(repair);
////        System.out.println("Updated PropertyRepair: " + updatedRepair);
////        System.out.println("--------------UPDATE REPAIR RESULT");
////        System.out.println("-------------------- REPAIR DELETE---------------");
////        propertyRepairService.deletePropertyRepair(1L);
////        System.out.println("---------REPAIR DELETED--------------");
////        System.out.println("---------------------SEARCH BY DATE RANGE REPAIR-----------------");
////        String startDate = "2024-08-01 00:00:00";
////        String endDate = "2024-08-31 23:59:59";
////        List<PropertyRepair> findByDateRangeRepair = propertyRepairService.searchPropertyRepairsByDateRange(startDate, endDate); // Start transaction
////        findByDateRangeRepair.forEach(System.out::println);
////        System.out.println("------------SEARCH BY DATE RANGE RESULT-------------------------");
////        System.out.println("--------------FIND BY OWNER ID REPAIR-------------------");
////        List<PropertyRepair> findByIdRepair = propertyRepairService.searchPropertyRepairsByOwnerId(1L);
////        findByIdRepair.forEach(System.out::println);
////        System.out.println("--------------FIND BY OWNER ID RESULT--------------------");
////        System.out.println("----------------------- GET REPAIRS BY OWNER VAT-------------------");
////        List<PropertyRepair> findRepairOwnersByVat = propertyRepairService.getOwnerRepairs("123456789");
////        System.out.println("---------------------GET REPAIRS BY OWNER VAT RESULT");
////
////        System.out.println("---------------------GET UNANSWERED REPAIRS BY OWNER VAT-------------------------");
////        List<PropertyRepair> findUnansweredRepairOwnersByVat = propertyRepairService.getUnansweredOwnerRepairs("123456789");
////        System.out.println("----------------------GET UNANSWERED REPAIRS BY OWNER VAT RESULT------------------------------------");
////        try {
//        //            // Save PropertyRepair
//        //            Long repairId = propertyRepairService.savePropertyRepair(repair);
//        //            System.out.println("------------------------------------------------");
//        //            System.out.println("Created PropertyRepair with ID: " + repairId);
//        //            // Commit transaction
//        //
//        //            // Retrieve and display PropertyRepair
//        //            //  PropertyRepair retrievedRepair = em.find(PropertyRepair.class, repairId);
//        //            //if (retrievedRepair != null) {
//        //            // System.out.println("Retrieved PropertyRepair: " + retrievedRepair);
//        //            //   } else {
//        //            //   System.out.println("PropertyRepair not found.");
//        //            //  }
//        //            // Retrieve all PropertyRepairs
//        //            //    List<PropertyRepair> allRropertyRepairs: " + allRepairs);
//        //        } catch (Exception e) {
//        //            System.out.println("--------------------------- ERROR--------------------");
//        //        } finally {
//        //            // Clean up resourcesepairs = propertyRepairService.getPropertyRepairs();
//        //            //      System.out.println("All P
//        //            em.close();
//        ////            emf.close();
//        //        }
////        System.out.println("----------------------- GET REPAIRS BY OWNER VAT-------------------");
////
////        System.out.println("---------------------GET REPAIRS BY OWNER VAT RESULT");
//
//        System.out.println("-----------------------!!!WELCOME TO TECHNIKON!!!-----------------------\n");
//        User user;
//        System.out.println("Would you like to use the application as a Property Owner(P) or as ans Administrator(A)?");
//        Scanner scanner = new Scanner(System.in);
//        String ans = scanner.next();
//        if (ans.toUpperCase().equals("A") || ans.toUpperCase().equals("ADMIN") || ans.toUpperCase().equals("ADMINISTRATOR")) {
//            System.out.println("Sign In as Administrator...");
//            user = new AdminUI();
//        }else{
//            System.out.println("Sign In as Property Owner...");
//            OwnerUI ownerUser = new OwnerUI();
//            PropertyOwner signedInOwner = ownerUser.signIn();
//            ownerUser.setOwner(signedInOwner);
//            user = ownerUser;
//        }
//        user.UserMenu();
//        System.out.println("\n------------------------APPLICATION TERMINATING------------------------");
//        System.out.println("Have a nice day!");
    }
}