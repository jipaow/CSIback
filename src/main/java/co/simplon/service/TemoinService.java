package co.simplon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.dao.SuspectDAO;
import co.simplon.dao.TemoinDAO;
import co.simplon.dao.VictimeDAO;
import co.simplon.model.Victime;
import co.simplon.model.SuspectEnquete;
import co.simplon.model.Temoin;
import co.simplon.model.Victime;

@Service
public class TemoinService {
	
	@Autowired
	private TemoinDAO dao;
	
	// Retrieve all rows from table and populate list with objects
	public List<Temoin> getAllTemoin() throws Exception {
		return dao.listTemoin();
	}
	
	// Retrieves one row from table based on given id
	public Temoin getTemoin(int id) throws Exception {
		return dao.getTemoin(id);
	}
	
	// Inserts row into table 
	public Temoin insertTemoin(Temoin temoin) throws Exception {
		return dao.insertTemoin(temoin);
	}
	
	public Temoin updateTemoin( int id ,Temoin temoin) throws Exception {
		return dao.updateTemoin(temoin);
	}
	

}
