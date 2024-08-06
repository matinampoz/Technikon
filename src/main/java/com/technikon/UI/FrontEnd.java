package com.technikon.UI;

import com.technikon.jpa.JpaUtil;
import com.technikon.models.PropertyOwner;
import com.technikon.repositories.OwnerRepository;
import com.technikon.services.OwnerService;
import com.technikon.services.OwnerServiceImpl;
import java.util.Scanner;

public class FrontEnd {
    
    public static PropertyOwner createNewPropertyOwner(){
        OwnerRepository oRep = new OwnerRepository(JpaUtil.getEntityManager());
        OwnerService ownerService = new OwnerServiceImpl(oRep);
        
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
        
        
        return ownerService.createOwner(vat, name, surname, address, 0, email, username, password);
    }
}
