package co.simplon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.dao.AgentDAO;
import co.simplon.model.Agent;


public class AgentService {

	@Autowired
	private AgentDAO dao;
	
	// Retrieve all rows from table and populate list with objects
	public List<Agent> getAllAgent() throws Exception {
		return dao.listAgent();
	}
	
	// Retrieves one row from table based on given id
	public Agent getAgent(int id) throws Exception {
		return dao.getAgent(id);
	}
	
	// Inserts row into table 
	public Agent insertAgent(Agent agent) throws Exception {
		return dao.insertAgent(agent);
	}
	
	public Agent updateAgent( int id ,Agent agent) throws Exception {
		return dao.updateAgent(agent);
	}
}
