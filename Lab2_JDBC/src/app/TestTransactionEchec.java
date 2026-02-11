package app;

import entities.Employe;
import entities.Machine;
import dao.EmployeDao;
import dao.MachineDao;
import util.Connexion;

import java.sql.Connection;

public class TestTransactionEchec {
    public static void main(String[] args) {
        Connection conn = null;
        
        try {
            conn = Connexion.getInstance().getConnection();
            conn.setAutoCommit(false);
            System.out.println(" Transaction démarrée...\n");
            
            // Créer un employé
            Employe e = new Employe("Karim", "Technicien");
            EmployeDao empDao = new EmployeDao();
            empDao.insert(e);
            System.out.println("Employé créé : " + e.getNom() + " (ID=" + e.getId() + ")");
            
            //  SIMULATION D'ERREUR : Machine avec nom NULL
            System.out.println(" Tentative de création d'une machine avec nom NULL...");
            Machine m = new Machine(null, "Test", e);  // ← NOM NULL → VA ÉCHOUER
            MachineDao machDao = new MachineDao();
            machDao.insert(m);
            
            // Cette ligne ne sera JAMAIS exécutée à cause de l'erreur
            conn.commit();
            System.out.println(" Transaction validée");
            
        } catch (Exception ex) {
            System.err.println("\n ERREUR CAPTURÉE !");
            System.err.println("   Message : " + ex.getMessage());
            
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("\nROLLBACK effectué avec succès");
                    System.out.println("  L'employé 'Karim' N'A PAS été enregistré");
                    System.out.println("  La machine N'A PAS été enregistrée");
                    System.out.println("  La base est restée INTACTE");
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    System.out.println("\n Auto-commit réactivé");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
