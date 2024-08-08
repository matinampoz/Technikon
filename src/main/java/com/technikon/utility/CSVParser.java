package com.technikon.utility;

import com.technikon.exceptions.PropertyException;
import com.technikon.jpa.JpaUtil;
import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import com.technikon.repositories.OwnerRepository;
import com.technikon.repositories.PropertyRepairRepository;
import com.technikon.repositories.PropertyRepository;
import com.technikon.services.OwnerService;
import com.technikon.services.OwnerServiceImpl;
import com.technikon.services.PropertyRepairService;
import com.technikon.services.PropertyRepairServiceImpl;
import com.technikon.services.PropertyService;
import com.technikon.services.PropertyServiceImpl;
import enums.PropertyType;
import enums.RepairType;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVParser {

    private OwnerRepository oRep;
    private OwnerService propertyOwnerService;
    private PropertyRepository pRep;
    private PropertyService propertyService;
    private PropertyRepairRepository rRep;
    private PropertyRepairService propertyRepairService;

    public CSVParser() {
        this.oRep = new OwnerRepository(JpaUtil.getEntityManager());
        this.propertyOwnerService = new OwnerServiceImpl(oRep);
        this.pRep = new PropertyRepository(JpaUtil.getEntityManager());
        this.propertyService = new PropertyServiceImpl(pRep);
        this.rRep = new PropertyRepairRepository(JpaUtil.getEntityManager());
        this.propertyRepairService = new PropertyRepairServiceImpl(rRep);
    }

    public void loadOwners(String fileName) {
        System.out.println("--------ENTERING LOAD OWNERS--------------");
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
        System.out.println("--------------OWNERS LOADED------------------------------");
    }

    public void loadProperties(String fileName) {
        System.out.println("--------------------ENTERING LOAD PROPERTIES------------------------");
        List<PropertyOwner> owners = this.propertyOwnerService.getAllOwners();
        try {
            Scanner readerFile = new Scanner(new File(fileName));

            while (readerFile.hasNextLine()) {
                String line = readerFile.nextLine();
                String[] fields = line.split(",");

                if (fields.length == 5) {
                    Optional<PropertyOwner> owner = owners.stream().filter(o -> o.getVatNumber().equals(fields[4])).findFirst();
                    if (owner.isEmpty()) {
                        System.out.println("OWNER WITH VAT:" + fields[4] + " NOT FOUND");
                        System.out.println("SKIPPING LINE...");
                    } else {
                        Property property = propertyService.createProperty(
                                fields[0],
                                fields[1],
                                Integer.parseInt(fields[2]),
                                PropertyType.valueOf(fields[3]),
                                owner.get());

                        propertyService.saveProperty(property);
                    }

                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR ON LOADING PROPERTIES");
        } catch (PropertyException ex) {
            Logger.getLogger(CSVParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("-------------------------PROPERTIES LOADED----------------------------");
    }

    public void loadRepairs(String fileName) {
        System.out.println("-------------------------ENTERING REPAIRS---------------------------------");

        try {
            System.out.println("----ABOUT TO CREATE SCANNER--------");
            Scanner readerFile = new Scanner(new File(fileName));
            System.out.println("------------ABOUT TO ENTER WHILE-----------");
            while (readerFile.hasNextLine()) {
                System.out.println("---------ENTERED WHILEE-----------------------");
                String line = readerFile.nextLine();
                String[] fields = line.split(",");
                if (fields.length == 8) {
                    System.out.println("---------ENTERED IF---------------");
                    Optional<Property> propertyOpt = propertyService.getAllProperties().stream()
                            .filter(p -> p.getE9().equals(fields[0]))
                            .findFirst();

                    if (propertyOpt.isEmpty()) {
                        System.out.println("PROPERTY WITH E9:" + fields[0] + " NOT FOUND");
                        System.out.println("SKIPPING LINE...");

                    } else {

                        LocalDateTime now = LocalDateTime.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        String timestamp = now.format(formatter);

                        PropertyRepair repair = propertyRepairService.createPropertyRepair(
                                propertyOpt.get(),
                                RepairType.valueOf(fields[1]),
                                timestamp,
                                fields[2],
                                fields[3],
                                fields[4],
                                fields[5],
                                Double.parseDouble(fields[6]),
                                Boolean.parseBoolean(fields[7])
                        );
                        propertyRepairService.savePropertyRepair(repair);
                    }

                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR ON LOADING REPAIRS");
        }
        System.out.println("------------------------REPAIRS LOADED-------------------------------");
    }
}