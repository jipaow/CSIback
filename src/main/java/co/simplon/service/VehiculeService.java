package co.simplon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.dao.VehiculeDAO;
import co.simplon.model.Vehicule;

@Service
public class VehiculeService {

	@Autowired
	private VehiculeDAO dao;
	
	// Retrieve all rows from table and populate list with objects
	public List<Vehicule> getAllVehicule() throws Exception {
		return dao.listVehicule();
	}
	
	// Retrieves one row from table based on given id
	public Vehicule getVehicule(int id) throws Exception {
		return dao.getVehicule(id);
	}
	
	// Inserts row into table 
	public Vehicule insertVehicule(Vehicule vehicule) throws Exception {
		return dao.insertVehicule(vehicule);
	}
	
	public Vehicule updateVehicule( int id ,Vehicule vehicule) throws Exception {
		return dao.updateVehicule(vehicule);
	}
}
