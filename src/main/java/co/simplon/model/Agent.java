package co.simplon.model;

import java.sql.Date;

public class Agent extends Humain {

	private String grade;
	private String competences;
	private boolean actif;
	private Date priseService;
	
	
	public Agent(int id, String nom, String prenom, Date dateNaissance, String genre, String grade, String competences,
			boolean actif, Date priseService) {
		super(id, nom, prenom, dateNaissance, genre);
		this.grade = grade;
		this.competences = competences;
		this.actif = actif;
		this.priseService = priseService;
	}
	
	public Agent (){
		super();
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCompetences() {
		return competences;
	}

	public void setCompetences(String competences) {
		this.competences = competences;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Date getPriseService() {
		return priseService;
	}

	public void setPriseService(Date priseService) {
		this.priseService = priseService;
	}
	
	
	
}
