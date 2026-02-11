package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connexion {
    private static Connexion instance;
    private final Connection conn;

    private static final String URL  = "jdbc:mysql://localhost:3306/atelier?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connexion() throws SQLException {
        conn = DriverManager.getConnection(URL, USER, PASS);
        conn.setAutoCommit(true);
    }

    public static synchronized Connexion getInstance() throws SQLException {
        if (instance == null || instance.conn.isClosed()) {
            instance = new Connexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
}