package co.simplon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.dao.ArmeDAO;
import co.simplon.model.Arme;

@Service
public class ArmeService {
	
	@Autowired
	private ArmeDAO dao;
	
	// Retrieve all rows from table and populate list with objects
	public List<Arme> getAllArme() throws Exception {
		return dao.listArme();
	}
	
	// Retrieves one row from table based on given id
	public Arme getArme(int id) throws Exception {
		return dao.getArme(id);
	}
	
	// Inserts row into table 
	public Arme insertArme(Arme arme) throws Exception {
		return dao.insertArme(arme);
	}
	
	public Arme updateArme( int id ,Arme arme) throws Exception {
		return dao.updateArme(arme);
	}
	

}

