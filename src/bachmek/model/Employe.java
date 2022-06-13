package bachmek.model;
public class Employe{
    protected int idemploye;
	protected String nom;
	protected String prenom;
	protected String email;
	protected String mdps;
	protected String role;
	protected double salaire;
	public Employe() {}
	public Employe(int idemploye,String nom,String prenom, String email, String mdps,String role,double salaire) {
        super();
        this.idemploye=idemploye;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdps = mdps;
        this.role=role;
        this.salaire=salaire;
	}
	public Employe(String nom,String prenom, String email, String mdps,String role,double salaire) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdps = mdps;
        this.role=role;
        this.salaire=salaire;
	}
	
	public Employe(int idemploye,String nom,String prenom, String email,String role,double salaire) {
		super();
        this.idemploye=idemploye;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email; 
        this.role=role;
        this.salaire=salaire;
		
	}
	public Employe(String nom,String prenom, String email,String role,double salaire) {
		super();
		this.nom = nom;
        this.prenom = prenom;
        this.email = email; 
        this.role=role;
        this.salaire=salaire;
	}
	public int getIdemploye() {
        return idemploye;
    }
    public void setIdemploye(int idemploye) {
        this.idemploye = idemploye;
    }
    
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    
    public String getMdps() {
        return mdps;
    }
    public void setMdps(String mdps) {
        this.mdps = mdps;
    }
    
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    
    public double getSalaire() {
        return salaire;
    }
    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }
}

