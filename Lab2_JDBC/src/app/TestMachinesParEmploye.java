package app;

import entities.Employe;
import entities.Machine;
import service.EmployeService;
import service.MachineService;

public class TestMachinesParEmploye {
    public static void main(String[] args) {
        EmployeService eSvc = new EmployeService();
        MachineService mSvc = new MachineService();
        
        try {
            // Créer un employé
            Employe emp = new Employe("Hassan", "Ingénieur");
            eSvc.createEmploye(emp);
            System.out.println("Employé créé : " + emp.getNom() + " (ID=" + emp.getId() + ")");
            
            // Créer 3 machines pour cet employé
            Machine m1 = new Machine("Fraiseuse F100", "Fraisage", emp);
            Machine m2 = new Machine("Tourneuse T200", "Tournage", emp);
            Machine m3 = new Machine("Perceuse P50", "Perçage", emp);
            
            mSvc.createMachine(m1);
            mSvc.createMachine(m2);
            mSvc.createMachine(m3);
            System.out.println("3 machines créées\n");
            
            // BONUS : Lister les machines de cet employé
            System.out.println("Machines de l'employé " + emp.getNom() + " :");
            
            mSvc.listMachinesByEmploye(emp).forEach(machine -> 
                System.out.printf("  %s [%s] - ID:%d%n", 
                    machine.getNom(), 
                    machine.getType(), 
                    machine.getId())
            );
            
            System.out.println("\n Test terminé avec succès !");
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
