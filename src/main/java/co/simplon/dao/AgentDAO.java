package co.simplon.dao;

import java.util.List;

import co.simplon.model.Agent;

public interface AgentDAO {

	
public List<Agent> listAgent() throws Exception;
	
	public Agent getAgent(int id) throws Exception;
			
	public Agent insertAgent(Agent agent) throws Exception;
			
	public Agent updateAgent(Agent agent) throws Exception;
}
