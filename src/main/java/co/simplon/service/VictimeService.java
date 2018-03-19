package co.simplon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.dao.VictimeDAO;
import co.simplon.model.Victime;

@Service
public class VictimeService {
	
	@Autowired
	private VictimeDAO dao;
	
	// Retrieve all rows from table and populate list with objects
	public List<Victime> getAllVictime() throws Exception {
		return dao.listVictime();
	}
	
	// Retrieves one row from table based on given id
	public Victime getVictime(int id) throws Exception {
		return dao.getVictime(id);
	}
	
	// Inserts row into table 
	public Victime insertVictime(Victime victime) throws Exception {
		return dao.insertVictime(victime);
	}
	
	public Victime updateVictime( int id ,Victime victime) throws Exception {
		return dao.updateVictime(victime);
	}
	

}
