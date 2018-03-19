package co.simplon.model;

import java.sql.Date;

public class Temoin extends Humain {

	private String origine;
	private String adresse;
	private Date dateDeces;
	private int telephone;
	private String temoignage;
	private int idEnquete;
	
	

	
	public Temoin(int id, String nom, String prenom, Date dateNaissance, String genre, String origine, String adresse,
			Date dateDeces, int telephone, String temoignage, int idEnquete) {
		super(id, nom, prenom, dateNaissance, genre);
		this.origine = origine;
		this.adresse = adresse;
		this.dateDeces = dateDeces;
		this.telephone = telephone;
		this.temoignage = temoignage;
		this.idEnquete = idEnquete;
	}


	public Temoin(){
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


	public Date getDateDeces() {
		return dateDeces;
	}


	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}


	public int getTelephone() {
		return telephone;
	}


	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}


	public String getTemoignage() {
		return temoignage;
	}


	public void setTemoignage(String temoignage) {
		this.temoignage = temoignage;
	}


	public int getIdEnquete() {
		return idEnquete;
	}


	public void setIdEnquete(int idEnquete) {
		this.idEnquete = idEnquete;
	}
	
}
