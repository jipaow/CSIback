package co.simplon.dao;

import java.util.List;

import co.simplon.model.Victime;

public interface VictimeDAO {
	
public List<Victime> listVictime() throws Exception;
	
	public Victime getVictime(int id) throws Exception;
	
	public Victime insertVictime(Victime victime) throws Exception;
	
	public Victime updateVictime(Victime victime) throws Exception;

}
