package bachmek.model;
public class Poste {
String nom;
String description;
int employe;
public Poste(String nom,String description) {
	this.nom=nom;
	this.description=description;
	this.employe=-1;
}
public void affecter(int e) {
	this.employe=e;
}
public boolean verifier() {
	if(this.employe==-1)
		return false;
	else
		return true;
}
public int liberer() {
	int e = this.getEmploye();
	this.setEmploye(-1);
	return e;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getEmploye() {
	return employe;
}
public void setEmploye(int employe) {
	this.employe = employe;
}
}
