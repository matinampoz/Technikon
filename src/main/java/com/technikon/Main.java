package com.technikon;

import com.technikon.UI.AdminUI;
import com.technikon.UI.OwnerUI;
import com.technikon.UI.User;
import com.technikon.jpa.JpaUtil;
import com.technikon.models.PropertyOwner;
import com.technikon.repositories.OwnerRepository;
import com.technikon.repositories.PropertyRepairRepository;
import com.technikon.repositories.PropertyRepository;
import com.technikon.services.OwnerServiceImpl;
import com.technikon.services.PropertyRepairServiceImpl;
import com.technikon.services.PropertyServiceImpl;
import com.technikon.utility.CSVParser;
import java.util.Scanner;
import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        OwnerRepository ownerRepository = new OwnerRepository(em);
        OwnerServiceImpl ownerService = new OwnerServiceImpl(ownerRepository);
        PropertyRepository propertyRepository = new PropertyRepository(em);
        PropertyServiceImpl propertyService = new PropertyServiceImpl(propertyRepository);
        PropertyRepairRepository propertyRepairRepository = new PropertyRepairRepository(em);
        PropertyRepairServiceImpl propertyRepairService = new PropertyRepairServiceImpl(propertyRepairRepository);

        CSVParser parser = new CSVParser();
        String fileName = "src/main/resources/OwnerData.csv";
        parser.loadOwners(fileName);
        fileName = "src/main/resources/PropertyData.csv";
        parser.loadProperties(fileName);
        fileName = "src/main/resources/RepairData.csv";
        parser.loadRepairs(fileName);

        System.out.println("-----------------------!!!WELCOME TO TECHNIKON!!!-----------------------\n");
        User user;
        System.out.println("Would you like to use the application as a Property Owner(P) or as ans Administrator(A)?");
        Scanner scanner = new Scanner(System.in);
        String ans = scanner.next();
        if (ans.toUpperCase().equals("A") || ans.toUpperCase().equals("ADMIN") || ans.toUpperCase().equals("ADMINISTRATOR")) {
            System.out.println("Sign In as Administrator...");
            user = new AdminUI();
        } else {
            System.out.println("Sign In as Property Owner...");
            OwnerUI ownerUser = new OwnerUI();
            PropertyOwner signedInOwner = ownerUser.signIn();
            ownerUser.setOwner(signedInOwner);
            user = ownerUser;
        }
        user.UserMenu();
        System.out.println("\n------------------------APPLICATION TERMINATING------------------------");
        System.out.println("Have a nice day!");
    }
}
