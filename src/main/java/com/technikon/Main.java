package com.technikon;

import com.technikon.jpa.JpaUtil;
import com.technikon.models.Property;
import com.technikon.repositories.OwnerRepository;
import com.technikon.repositories.PropertyRepairRepository;
import com.technikon.repositories.PropertyRepository;
import enums.PropertyType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // log.info("CRM application starting...");

        /*EntityManager em = JpaUtil.getEntityManager();

        OwnerRepository ownerRepository = new OwnerRepository(em);
        PropertyRepository propertyRepository = new PropertyRepository(em);
        PropertyRepairRepository propertyRepairRepository = new PropertyRepairRepository(em);*/

        //  Property property = Property.builder()
        //          .propertyID(1L)
        //         .address("123 Test Street")
        //        .yearOfConstruction(2022)
        //          .typeOfProperty(PropertyType.MAISONETTE) // Ensure this enum value exists
        //          .ownerVAT(123456789L)
        //          .build();
        //          TESTING 
        // Perform Create operation
        //    EntityTransaction transaction = em.getTransaction();
        //   transaction.begin();
        //  em.persist(property);
        //  transaction.commit();
        //    System.out.println("Property created: " + property);
        // Perform Read operation
//        Property foundProperty = em.find(Property.class, property.getPropertyID());
//        System.out.println("Found property: " + foundProperty);
//
//        // Update the Property
//        transaction.begin();
//        foundProperty.setAddress("456 Updated Street");
//        em.merge(foundProperty);
//        transaction.commit();
//        System.out.println("Updated property: " + foundProperty);
        // Delete the Property
//        transaction.begin();
//        Property propertyToDelete = em.find(Property.class, foundProperty.getPropertyID());
//        if (propertyToDelete != null) {
//            em.remove(propertyToDelete);
//            transaction.commit();
//            System.out.println("Property deleted.");
//        }
        //  OwnerService ownerService = new OwnerServiceImpl(ownerRepository);
        //PropertyService propertyService = new PropertyServiceImpl(propertyRepository);
        // PropertyRepairService propertyRepairService = new PropertyRepairServiceImpl(propertyRepairRepository);
       // em.close();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
        EntityManager em = emf.createEntityManager();
        emf.close();

        System.out.println("Hello");
    }
}
