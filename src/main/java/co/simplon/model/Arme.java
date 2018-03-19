package co.simplon.model;

public class Arme {

	
	private int idArme;
	private String type;
	private int numeroDeSerie;
	
	public Arme(int idArme, String type, int numeroDeSerie) {
		super();
		this.idArme = idArme;
		this.type = type;
		this.numeroDeSerie = numeroDeSerie;
	}
	
	public Arme(){
		super();
	}

	public int getIdArme() {
		return idArme;
	}

	public void setIdArme(int idArme) {
		this.idArme = idArme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(int numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
}
