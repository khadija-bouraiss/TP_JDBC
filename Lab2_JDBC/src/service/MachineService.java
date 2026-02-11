package service;

import dao.MachineDao;
import entities.Machine;
import entities.Employe;

import java.util.List;

public class MachineService {
    private final MachineDao dao = new MachineDao();

    public Machine getMachine(Machine m) throws Exception {
        return dao.findById(m.getId());
    }

    public List<Machine> listMachines() throws Exception {
        return dao.findAll();
    }

    public Machine createMachine(Machine m) throws Exception {
    if (m.getNom() == null || m.getNom().trim().isEmpty()) {
        throw new Exception("ERREUR : Le nom de la machine ne peut pas être vide");
    }
    if (m.getType() == null || m.getType().trim().isEmpty()) {
        throw new Exception("ERREUR : Le type de la machine ne peut pas être vide");
    }
    if (m.getEmploye() == null || m.getEmploye().getId() <= 0) {
        throw new Exception("ERREUR : La machine doit avoir un employé existant");
    }
    
    dao.insert(m);
    return m;
}

    public boolean updateMachine(Machine m) throws Exception {
        return dao.update(m);
    }

    public boolean deleteMachine(Machine m) throws Exception {
        return dao.delete(m.getId());
    }
    
    //  BONUS : Lister les machines d'un employé
    public List<Machine> listMachinesByEmploye(Employe e) throws Exception {
        return dao.findByEmploye(e.getId());
    }
}
