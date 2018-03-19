package co.simplon.dao;

import java.util.List;

import co.simplon.model.Arme;

public interface ArmeDAO {

	
	public List<Arme> listArme() throws Exception;
	
	public Arme getArme(int id) throws Exception;
			
	public Arme insertArme(Arme arme) throws Exception;
			
	public Arme updateArme(Arme arme) throws Exception;
}
