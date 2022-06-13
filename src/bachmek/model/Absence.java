package bachmek.model;
public class Absence {
	int idabsence;
	String dateabsence;
	int justif;
	int idemploye;
public Absence() {}
public Absence(int idabsence,String dateabsence,int idemploye) {
	this.idabsence=idabsence;
	this.dateabsence=dateabsence;
	this.justif=0;
	this.idemploye=idemploye;
}
public Absence(int idabsence,String dateabsence,int idemploye,int justif) {
	this.idabsence=idabsence;
	this.dateabsence=dateabsence;
	this.justif=justif;
	this.idemploye=idemploye;
}
public Absence(String dateabsence,int idemploye) {
	this.dateabsence=dateabsence;
	this.justif=0;
	this.idemploye=idemploye;
}
public int getIdabsence() {
    return idabsence;
}
public void setIdabsence(int idabsence) {
    this.idabsence = idabsence;
}
public String getDateabsence() {
	return dateabsence;
}
public void setDateabsence(String dateabsence) {
	this.dateabsence=dateabsence;
}
public int getJustif() {
	return justif;
}
public void setJustif(int justif) {
	this.justif=justif;
}

public int getIdemploye() {
    return idemploye;
}
public void setIdemploye(int idemploye) {
    this.idemploye = idemploye;
}
}
