package co.simplon.dao;

import java.util.List;

import co.simplon.model.Temoin;

public interface TemoinDAO {
	
public List<Temoin> listTemoin() throws Exception;
	
	public Temoin getTemoin(int id) throws Exception;
	
	public Temoin insertTemoin(Temoin temoin) throws Exception;
	
	public Temoin updateTemoin(Temoin temoin) throws Exception;

}
