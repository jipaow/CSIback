package co.simplon.dao;

import java.util.List;

import co.simplon.model.Vehicule;

public interface VehiculeDAO {

public List<Vehicule> listVehicule() throws Exception;
	
	public Vehicule getVehicule(int id) throws Exception;
	
	public Vehicule insertVehicule(Vehicule vehicule) throws Exception;
	
	public Vehicule updateVehicule(Vehicule vehicule) throws Exception;
	
}
