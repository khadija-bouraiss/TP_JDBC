package entities;

public class Machine {
    private int id;
    private String nom;
    private String type;
    private Employe employe;

    public Machine() {}

    public Machine(String nom, String type, Employe employe) {
        this.nom = nom;
        this.type = type;
        this.employe = employe;
    }

    public Machine(int id, String nom, String type, Employe employe) {
        this(nom, type, employe);
        this.id = id;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Employe getEmploye() { return employe; }
    public void setEmploye(Employe employe) { this.employe = employe; }
}