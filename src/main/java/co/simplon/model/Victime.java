package co.simplon.model;

import java.sql.Date;

public class Victime extends Humain {

	private String origine;
	private String adresse;
	private String victimeDe;
	private Date dateDeces;
	private String signeDistinctif;
	private String photo;
	private int idEnquete;
	
	
	public Victime(int id, String nom, String prenom, java.sql.Date dateNaissance, String genre, String origine,
			String adresse, String victimeDe, Date dateDeces, String signeDistinctif, String photo, int idEnquete) {
		super(id, nom, prenom, dateNaissance, genre);
		this.origine = origine;
		this.adresse = adresse;
		this.victimeDe = victimeDe;
		this.dateDeces = dateDeces;
		this.signeDistinctif = signeDistinctif;
		this.photo = photo;
		this.idEnquete = idEnquete;
	}
	
	public Victime(){
		super();
	}
	
	
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVictimeDe() {
		return victimeDe;
	}
	public void setVictimeDe(String victimeDe) {
		this.victimeDe = victimeDe;
	}
	public Date getDateDeces() {
		return dateDeces;
	}
	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}
	public String getSigneDistinctif() {
		return signeDistinctif;
	}
	public void setSigneDistinctif(String signeDistinctif) {
		this.signeDistinctif = signeDistinctif;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getIdEnquete() {
		return idEnquete;
	}
	public void setIdEnquete(int idEnquete) {
		this.idEnquete = idEnquete;
	}
	
	
	
}
