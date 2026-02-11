package app;

import entities.Employe;
import entities.Machine;
import service.EmployeService;
import service.MachineService;

public class TestMachine {
    public static void main(String[] args) {
        MachineService mSvc = new MachineService();
        EmployeService eSvc = new EmployeService();

        try {
            Employe e = new Employe("Maroanin", "Technicien");
            eSvc.createEmploye(e);

            Machine m = new Machine("TourneuseX", "Usinage", e);
            mSvc.createMachine(m);
            System.out.println("Machine créée : ID=" + m.getId());

            System.out.println("\nListe des machines :");
            mSvc.listMachines().forEach(machine ->
                    System.out.printf("%d : %s [%s] → Employé=%s (%d)%n",
                            machine.getId(), machine.getNom(), machine.getType(),
                            machine.getEmploye().getNom(), machine.getEmploye().getId())
            );

            m.setNom("TourneuseX-Pro");
            m.setType("Usinage avancé");
            System.out.println("\nUpdate OK ? " + mSvc.updateMachine(m));

            System.out.println("Delete machine OK ? " + mSvc.deleteMachine(m));

            // Si on supprime l'employé, ses machines ont déjà été supprimées automatiquement (CASCADE)
            System.out.println("Delete employé OK ? " + eSvc.deleteEmploye(e));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}