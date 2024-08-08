package com.technikon.utility;

import com.technikon.jpa.JpaUtil;
import com.technikon.models.PropertyOwner;
import com.technikon.repositories.OwnerRepository;
import com.technikon.services.OwnerService;
import com.technikon.services.OwnerServiceImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OwnerParser {

    public void loadOwners(String fileName) {

        OwnerRepository pRep = new OwnerRepository(JpaUtil.getEntityManager());
        OwnerService propertyOwnerService = new OwnerServiceImpl(pRep);
        try {
            Scanner readerFile = new Scanner(new File(fileName));

            while (readerFile.hasNextLine()) {
                String line = readerFile.nextLine();
                String[] fields = line.split(",");
                if (fields.length == 8) {
                    PropertyOwner owner = propertyOwnerService.createOwner(
                            fields[0],
                            fields[1],
                            fields[2],
                            fields[3],
                            Long.parseLong(fields[4]),
                            fields[5],
                            fields[6],
                            fields[7]);
                    propertyOwnerService.saveOwner(owner);
                }

            }

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR ON LOADING OWNERS");
        }

    }
}
