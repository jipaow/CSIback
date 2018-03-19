package co.simplon.model;

public class Vehicule {

	private int id;
	private String type;
	private String marque;
	private String modele;
	private String immatriculation;
	private String couleur;
	
	public Vehicule(int id, String type, String marque, String modele, String immatriculation, String couleur) {
		super();
		this.id = id;
		this.type = type;
		this.marque = marque;
		this.modele = modele;
		this.immatriculation = immatriculation;
		this.couleur = couleur;
	}
	
	public Vehicule(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	
}
