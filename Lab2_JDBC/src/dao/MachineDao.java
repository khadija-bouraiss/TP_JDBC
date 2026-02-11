package dao;

import entities.Machine;
import entities.Employe;
import util.Connexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MachineDao implements IDao<Machine> {

    private final EmployeDao empDao = new EmployeDao();

    @Override
    public Machine findById(int id) throws Exception {
        String sql = "SELECT * FROM machine WHERE id=?";

        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int empId = rs.getInt("employe_id");
                    Employe e = empDao.findById(empId);

                    return new Machine(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("type"),
                            e
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<Machine> findAll() throws Exception {
        String sql = "SELECT * FROM machine";
        List<Machine> list = new ArrayList<>();

        try (Statement st = Connexion.getInstance().getConnection().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int empId = rs.getInt("employe_id");
                Employe e = empDao.findById(empId);

                list.add(new Machine(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("type"),
                        e
                ));
            }
        }
        return list;
    }

    @Override
    public int insert(Machine m) throws Exception {
        String sql = "INSERT INTO machine(nom,type,employe_id) VALUES(?,?,?)";

        try (PreparedStatement ps = Connexion.getInstance().getConnection()
                .prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getNom());
            ps.setString(2, m.getType());
            ps.setInt(3, m.getEmploye().getId());

            int rows = ps.executeUpdate();

            if (rows == 1) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        int id = keys.getInt(1);
                        m.setId(id);
                        return id;
                    }
                }
            }
            return -1;
        }
    }

    @Override
    public boolean update(Machine m) throws Exception {
        String sql = "UPDATE machine SET nom=?, type=?, employe_id=? WHERE id=?";

        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, m.getNom());
            ps.setString(2, m.getType());
            ps.setInt(3, m.getEmploye().getId());
            ps.setInt(4, m.getId());

            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM machine WHERE id=?";

        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        }
    }
    
    // BONUS : Lister les machines d'un employé
    public List<Machine> findByEmploye(int employeId) throws Exception {
        String sql = "SELECT * FROM machine WHERE employe_id = ?";
        List<Machine> list = new ArrayList<>();
        
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, employeId);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Employe e = empDao.findById(employeId);
                    
                    list.add(new Machine(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("type"),
                        e
                    ));
                }
            }
        }
        return list;
    }
}
