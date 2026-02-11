package service;

import dao.EmployeDao;
import entities.Employe;

import java.util.List;

public class EmployeService {
    private final EmployeDao dao = new EmployeDao();

    public Employe getEmploye(Employe e) throws Exception {
        return dao.findById(e.getId());
    }

    public List<Employe> listEmployes() throws Exception {
        return dao.findAll();
    }

    public Employe createEmploye(Employe e) throws Exception {
    if (e.getNom() == null || e.getNom().trim().isEmpty()) {
        throw new Exception("ERREUR : Le nom de l'employé ne peut pas être vide");
    }
    if (e.getPoste() == null || e.getPoste().trim().isEmpty()) {
        throw new Exception("ERREUR : Le poste de l'employé ne peut pas être vide");
    }
    
    dao.insert(e);
    return e;
}

    public boolean updateEmploye(Employe e) throws Exception {
        return dao.update(e);
    }

    public boolean deleteEmploye(Employe e) throws Exception {
        return dao.delete(e.getId());
    }
}
