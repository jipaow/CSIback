package co.simplon.model;

public class Suspect extends Humain {
	

	private String adresseConnues;
	private float taille;
	private int poid;
	private String signeDistinctif;
	private String nationalité;
	private boolean casierJudiciaire;
	private int condamnations;
	private String empreinte;
	private String typeCondamnation;
	private String photo;
	
	
	
	public Suspect(String adresseConnues, float taille, int poid, String signeDistinctif, String nationalité,
			boolean casierJudiciaire, int condamnations, String empreinte, String typeCondamnation, String photo) {
		super();
		this.adresseConnues = adresseConnues;
		this.taille = taille;
		this.poid = poid;
		this.signeDistinctif = signeDistinctif;
		this.nationalité = nationalité;
		this.casierJudiciaire = casierJudiciaire;
		this.condamnations = condamnations;
		this.empreinte = empreinte;
		this.typeCondamnation = typeCondamnation;
		this.photo = photo;
	}
	
	
	public Suspect() {
		super();
		// TODO Auto-generated constructor stub
	}

	//getters setters
	public String getEmpreinte() {
		return empreinte;
	}
	
	public void setEmpreinte(String empreinte) {
		this.empreinte = empreinte;
	}
	public String getAdresseConnues() {
		return adresseConnues;
	}
	public void setAdresseConnues(String adresseConnues) {
		this.adresseConnues = adresseConnues;
	}
	public float getTaille() {
		return taille;
	}
	public void setTaille(float taille) {
		this.taille = taille;
	}
	public int getPoid() {
		return poid;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
	public String getSigneDistinctif() {
		return signeDistinctif;
	}
	public void setSigneDistinctif(String signeDistinctif) {
		this.signeDistinctif = signeDistinctif;
	}
	public String getNationalité() {
		return nationalité;
	}
	public void setNationalité(String nationalité) {
		this.nationalité = nationalité;
	}
	public boolean isCasierJudiciaire() {
		return casierJudiciaire;
	}
	public void setCasierJudiciaire(boolean casierJudiciaire) {
		this.casierJudiciaire = casierJudiciaire;
	}
	public int getCondamnations() {
		return condamnations;
	}
	public void setCondamnations(int condamnations) {
		this.condamnations = condamnations;
	}
	public String getTypeCondamnation() {
		return typeCondamnation;
	}
	public void setTypeCondamnation(String typeCondamnation) {
		this.typeCondamnation = typeCondamnation;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	

}
