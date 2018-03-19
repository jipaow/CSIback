package co.simplon.dao;

import java.util.List;

import co.simplon.model.Suspect;
import co.simplon.model.SuspectEnquete;


public interface SuspectDAO {
	
	public List<Suspect> listSuspect () throws Exception;
	
	public Suspect  getSuspect(int id) throws Exception;
	
	public Suspect insertSuspect(Suspect suspect) throws Exception;
	
	public Suspect updateSuspect( Suspect suspect) throws Exception;
	
	public SuspectEnquete addSuspectToEnquete (SuspectEnquete suspectEnquete) throws Exception;
	
}
