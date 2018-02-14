package co.simplon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.dao.SuspectDAO;
import co.simplon.model.Enquete;
import co.simplon.model.Suspect;


@Service
public class SuspectService {
	
	@Autowired
	private SuspectDAO dao;
	
	// Retrieve all rows from table and populate list with objects
	public List<Suspect> getAllSuspect() throws Exception {
		return dao.listSuspect();
	}
	
	// Retrieves one row from table based on given id
	public Suspect getSuspect(String nom) throws Exception {
		return dao.getSuspect(nom);
	}
	
	// Inserts row into table 
	public Suspect insertSuspect(Suspect suspect) throws Exception {
		return dao.insertSuspect(suspect);
	}
	
	public Suspect upDateSuspect(Suspect suspect) throws Exception {
		return dao.updateSuspect(suspect);
	}
	
	public Suspect addSuspectToEnquete (Suspect suspect ,Enquete enquete) throws Exception {
		return dao.addSuspectToEnquete(suspect, enquete);
	}
	

}
