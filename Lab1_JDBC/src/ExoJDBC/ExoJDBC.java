package ExoJDBC;
import java.sql.*;
import java.util.Scanner;  

public class ExoJDBC {
    // Paramètres de connexion
    private static final String URL = "jdbc:mysql://localhost:3306/atelier?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL introuvable. Vérifier l'import du .jar.");
            e.printStackTrace();
            return;
        }
        // Connexion + Statement
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            System.out.println("Connexion MySQL OK.");

            // Réinitialiser la table
            stmt.executeUpdate("DROP TABLE IF EXISTS DevData");
            stmt.executeUpdate(
                    "CREATE TABLE DevData (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +  
                            "Developpeurs VARCHAR(32) NOT NULL, " +
                            "Jour VARCHAR(16) NOT NULL, " +
                            "NbScripts INT NOT NULL CHECK (NbScripts >= 0), " +  // ← VALIDATION AJOUTÉE
                            "UNIQUE KEY unique_dev_jour (Developpeurs, Jour)" +  // ← ANTI-DOUBLON AJOUTÉ
                            ")"
            );
            // Données de test
            stmt.executeUpdate("INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('ALAMI', 'Lundi', 1)");
            stmt.executeUpdate("INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('WAFI', 'Lundi', 2)");
            stmt.executeUpdate("INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('SLAMI', 'Mardi', 9)");
            stmt.executeUpdate("INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('ALAMI', 'Mardi', 3)");
            stmt.executeUpdate("INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('WAFI', 'Mardi', 4)");
            stmt.executeUpdate("INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('SLAMI', 'Mercredi', 2)");

            System.out.println("Table créée + données insérées.");
            
            // TEST DOUBLON
            System.out.println("\n Test : tentative d'insertion d'un doublon...");
            try {
            stmt.executeUpdate("INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('ALAMI', 'Lundi', 5)");
            System.out.println(" PROBLÈME : le doublon a été inséré (ne devrait pas !)");
            } 
            catch (SQLException e) {
            System.out.println("SUCCÈS : doublon refusé comme prévu !");
            System.out.println("  Erreur : " + e.getMessage());
            }  
            
            //TEST VALIDATION NÉGATIVE
            System.out.println("\nTest : tentative d'insertion d'un nombre négatif...");
            try {
            stmt.executeUpdate("INSERT INTO DevData (Developpeurs, Jour, NbScripts) VALUES ('TOTO', 'Jeudi', -5)");
            System.out.println(" PROBLÈME : valeur négative acceptée (ne devrait pas !)");
            } 
            catch (SQLException e) {
            System.out.println(" SUCCÈS : valeur négative refusée comme prévu !");
            System.out.println("   Erreur : " + e.getMessage());
            }
            
            // Statistique 1 : max par jour
            System.out.println("\n--- Max scripts par jour ---");
            try (ResultSet rs = stmt.executeQuery(
                    "SELECT Jour, Developpeurs, MAX(NbScripts) AS MaxScripts " +
                            "FROM DevData GROUP BY Jour"
            )) {
                while (rs.next()) {
                    String jour = rs.getString("Jour");
                    String dev = rs.getString("Developpeurs");
                    int max = rs.getInt("MaxScripts");
                    System.out.println(jour + " | " + dev + " | " + max);
                }
            }

            // Statistique 2 : classement par total décroissant
            System.out.println("\n--- Classement des développeurs (total scripts) ---");
            try (ResultSet rs = stmt.executeQuery(
                    "SELECT Developpeurs, SUM(NbScripts) AS Total " +
                            "FROM DevData GROUP BY Developpeurs ORDER BY Total DESC"
            )) {
                while (rs.next()) {
                    String dev = rs.getString("Developpeurs");
                    int total = rs.getInt("Total");
                    System.out.println(dev + " | " + total);
                }
            }

            // Statistique 3 : total semaine
            System.out.println("\n--- Total scripts semaine ---");
            try (ResultSet rs = stmt.executeQuery("SELECT SUM(NbScripts) AS TotalSemaine FROM DevData")) {
                if (rs.next()) {
                    System.out.println("Total semaine : " + rs.getInt("TotalSemaine"));
                }
            }

            // Statistique 3bis : moyenne scripts par jour ← NOUVELLE STATISTIQUE AJOUTÉE
            System.out.println("\n--- Moyenne scripts par jour ---");
            try (ResultSet rs = stmt.executeQuery(
                    "SELECT Jour, AVG(NbScripts) AS MoyenneJour " +
                            "FROM DevData GROUP BY Jour"
            )) {
                while (rs.next()) {
                    String jour = rs.getString("Jour");
                    double moyenne = rs.getDouble("MoyenneJour");
                    System.out.printf("%s | %.2f scripts en moyenne%n", jour, moyenne);
                }
            }

            // Statistique 4 : total pour un développeur (PreparedStatement avec Scanner) ← MODIFIÉ
            System.out.println("\n--- Total scripts pour un développeur (PreparedStatement) ---");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez le nom du développeur : ");
            String devRecherche = scanner.nextLine().toUpperCase();

            String sql = "SELECT SUM(NbScripts) AS TotalDev FROM DevData WHERE Developpeurs = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, devRecherche);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int totalDev = rs.getInt("TotalDev");
                        if (totalDev == 0 && rs.wasNull()) {
                            System.out.println("Aucun développeur trouvé avec ce nom.");
                        } else {
                            System.out.println("Total pour " + devRecherche + " : " + totalDev);
                        }
                    }
                }
            }
            scanner.close(); 

        } catch (SQLException e) {
            System.out.println("Erreur SQL : vérifier MySQL (base, user/password, port).");
            e.printStackTrace(); }  } }