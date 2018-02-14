package co.simplon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.dao.EnqueteDAO;
import co.simplon.model.Enquete;


@Service
public class ServiceEnquete {
	
	@Autowired
	private EnqueteDAO dao;
	
	// Retrieve all rows from table and populate list with objects
	public List<Enquete> getAllEnquete() throws Exception {
		return dao.listEnquete();
	}
	
	// Retrieves one row from table based on given id
	public Enquete getEnquete(int numeroDossier) throws Exception {
		return dao.getEnquete(numeroDossier);
	}
	
	// Inserts row into table 
	public Enquete addEnquete(Enquete enquete) throws Exception {
		return dao.insertEnquete(enquete);
	}
	
	// Updates row in table
	public Enquete updateEnquete(Enquete enquete) throws Exception {
		return dao.updateEnquete(enquete);
	}


}
