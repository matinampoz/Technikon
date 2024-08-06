package com.technikon.UI;

import com.technikon.models.Property;
import com.technikon.models.PropertyOwner;
import com.technikon.models.PropertyRepair;
import enums.RepairStatus;
import java.util.List;
import java.util.Scanner;

public class OwnerUI implements User {

    @Override
    public PropertyOwner createNewOwner() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Property createNewProperty() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PropertyRepair createNewPropertyRepair() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Property> getProperties() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PropertyRepair> getRepairs() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<PropertyRepair> getRepairs(RepairStatus status) {

        return null;

    }

    @Override
    public int getAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                           What would you like to do?
                           1) Display all my Properties.
                           2) Display Pending Repairs.
                           """);

        return 0;
    }

    public PropertyOwner signIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                           What would you like to do?
                           1) Create a new profile as a Property Owner.
                           2) Log In as an already existing Property Owner.
                           """);
        int choice = Integer.parseInt(scanner.next());
        PropertyOwner owner;
        if (choice == 1) {
            owner = 
        } else {
            //log in
        }

        return owner;
    }
}
