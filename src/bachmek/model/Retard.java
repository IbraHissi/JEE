package bachmek.model;
public class Retard {
	int idretard;
	String dateretard;
	int justif;
	int idemploye;
	public Retard() {}
	public Retard(int idretard,String dateretard,int idemploye) {
		this.idretard=idretard;
		this.dateretard=dateretard;
		this.justif=0;
		this.idemploye=idemploye;
	}
	public Retard(int idretard,String dateretard,int idemploye,int justif) {
		this.idretard=idretard;
		this.dateretard=dateretard;
		this.justif=justif;
		this.idemploye=idemploye;
	}
	public Retard(String dateretard,int idemploye) {
		this.dateretard=dateretard;
		this.justif=0;
		this.idemploye=idemploye;
	}
	public int getIdretard() {
        return idretard;
    }
    public void setIdretard(int idretard) {
        this.idretard = idretard;
    }
    public String getDateretard() {
    	return dateretard;
    }
    public void setDateretard(String dateretard) {
    	this.dateretard=dateretard;
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
