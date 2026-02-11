package app;

import entities.Employe;
import service.EmployeService;

public class TestEmploye {
    public static void main(String[] args) {
        EmployeService svc = new EmployeService();

        try {
            Employe e = new Employe("Maroanin", "Technicien");
            svc.createEmploye(e);
            System.out.println("Employé créé : ID=" + e.getId());

            System.out.println("\nListe des employés :");
            svc.listEmployes().forEach(emp ->
                    System.out.printf("%d : %s [%s]%n", emp.getId(), emp.getNom(), emp.getPoste())
            );

            e.setPoste("Superviseur");
            System.out.println("\nMise à jour OK ? " + svc.updateEmploye(e));

            System.out.println("Suppression OK ? " + svc.deleteEmploye(e));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}