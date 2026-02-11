package app;

import entities.Employe;
import entities.Machine;
import service.EmployeService;
import service.MachineService;

public class TestValidation {
    public static void main(String[] args) {
        EmployeService eSvc = new EmployeService();
        MachineService mSvc = new MachineService();
        
        System.out.println("TEST DES VALIDATIONS\n");
        
        // Test 1 : Nom employé vide
        System.out.println("--- Test 1 : Nom employé vide ---");
        try {
            Employe e1 = new Employe("", "Technicien");
            eSvc.createEmploye(e1);
            System.out.println("PROBLÈME : validation n'a pas fonctionné");
        } catch (Exception ex) {
            System.out.println("SUCCÈS : " + ex.getMessage());
        }
        
        // Test 2 : Poste employé vide
        System.out.println("\n--- Test 2 : Poste employé vide ---");
        try {
            Employe e2 = new Employe("Ahmed", "");
            eSvc.createEmploye(e2);
            System.out.println(" PROBLÈME : validation n'a pas fonctionné");
        } catch (Exception ex) {
            System.out.println("SUCCÈS : " + ex.getMessage());
        }
        
        // Test 3 : Machine sans employé
        System.out.println("\n--- Test 3 : Machine sans employé ---");
        try {
            Machine m1 = new Machine("TourneuseX", "Usinage", null);
            mSvc.createMachine(m1);
            System.out.println("PROBLÈME : validation n'a pas fonctionné");
        } catch (Exception ex) {
            System.out.println("SUCCÈS : " + ex.getMessage());
        }
        
        // Test 4 : Machine avec nom vide
        System.out.println("\n--- Test 4 : Machine avec nom vide ---");
        try {
            Employe e3 = new Employe("Test", "Opérateur");
            eSvc.createEmploye(e3);
            
            Machine m2 = new Machine("", "Usinage", e3);
            mSvc.createMachine(m2);
            System.out.println("PROBLÈME : validation n'a pas fonctionné");
        } catch (Exception ex) {
            System.out.println("SUCCÈS : " + ex.getMessage());
        }
        
        System.out.println("\nTous les tests de validation terminés !");
    }
}
