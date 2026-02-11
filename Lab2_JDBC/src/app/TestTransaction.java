package app;

import entities.Employe;
import entities.Machine;
import dao.EmployeDao;
import dao.MachineDao;
import util.Connexion;

import java.sql.Connection;

public class TestTransaction {
    public static void main(String[] args) {
        Connection conn = null;
        
        try {
            // Récupérer la connexion
            conn = Connexion.getInstance().getConnection();
            
            // DÉSACTIVER l'auto-commit (début de transaction)
            conn.setAutoCommit(false);
            System.out.println("Transaction démarrée...\n");
            
            // Créer un employé
            Employe e = new Employe("Youssef", "Superviseur");
            EmployeDao empDao = new EmployeDao();
            empDao.insert(e);
            System.out.println("Employé créé : " + e.getNom() + " (ID=" + e.getId() + ")");
            
            // Créer une machine pour cet employé
            Machine m = new Machine("Robot Industriel R500", "Robotique", e);
            MachineDao machDao = new MachineDao();
            machDao.insert(m);
            System.out.println("Machine créée : " + m.getNom() + " (ID=" + m.getId() + ")");
            
            // TOUT S'EST BIEN PASSÉ → VALIDER la transaction
            conn.commit();
            System.out.println("\n Transaction VALIDÉE avec succès !");
            System.out.println("   → Employé et Machine enregistrés dans la base");
            
        } catch (Exception ex) {
            // EN CAS D'ERREUR → ANNULER tout
            System.err.println("\n ERREUR détectée ! Annulation de la transaction...");
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println(" Transaction ANNULÉE (rollback)");
                    System.out.println("  Rien n'a été enregistré dans la base");
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            ex.printStackTrace();
            
        } finally {
            // TOUJOURS remettre auto-commit à true
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
