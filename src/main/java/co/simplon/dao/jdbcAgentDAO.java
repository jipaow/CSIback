package co.simplon.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import co.simplon.model.Agent;
import co.simplon.model.Agent;

public class jdbcAgentDAO implements AgentDAO {

	private DataSource datasource;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public jdbcAgentDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Agent> listAgent() throws Exception {
		Agent agent;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList <Agent> listAgent = new ArrayList<Agent>();
		
		try {
			// Prepare la requete sql
			sql = "SELECT * FROM agent";
			pstmt = datasource.getConnection().prepareStatement(sql);
			
			// Run la requete
			rs = pstmt.executeQuery();
			
			// Log info
			logSQL(pstmt);

			// gere le resultat de la requete
			while (rs.next()) {
				agent = getAgentFromResultSet(rs);
				listAgent.add(agent);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
		return listAgent;
	
	}

	@Override
	public Agent getAgent(int id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Agent agent = null;

		try {
			// Prepare requet sql
			String sql = "SELECT * FROM arme WHERE id_agent = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);

			// Log info
			logSQL(pstmt);

			// Run requete
			rs = pstmt.executeQuery();
			
			// gere les resultats de requete
			if (rs.next())
				agent = getAgentFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return agent;
	
	}

	@Override
	public Agent insertAgent(Agent agent) throws Exception {
		PreparedStatement pstmt = null;
		Agent result = null;
		int i = 0;
		
		// TODO
		// force auto incremente en initialisant à 0, sinon erreur sql si id
		// existant
		//suspect.setId(new int(0));

		try {
			// Prepare the SQL query
			String sql = "INSERT INTO agent ( nom, prenom, grade, competences, date_naissance, actif, date_prise_service) VALUES (?,?,?,?,?,?,?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++i, agent.getNom());
			pstmt.setString(++i, agent.getPrenom());
			pstmt.setString(++i, agent.getGrade());
			pstmt.setString(++i, agent.getCompetences());
			pstmt.setDate(++i, agent.getDateNaissance());
			pstmt.setBoolean(++i, agent.isActif());
			pstmt.setDate(++i, agent.getPriseService());
			
		// Log info
					logSQL(pstmt);
					
					// Run the the update query
					pstmt.executeUpdate();

					// TODO 
					// recupération de l'id genere, et maj de l'acteur avec l'id et la date de modif
//					ResultSet rs = pstmt.getGeneratedKeys();
//					if (rs.next()) {
//						suspect.setId(rs.getLong(1));
//						suspect.setLastUpdate(updateTime);
//						
//						result = suspect;
//					}
				} catch (SQLException e) {
					e.printStackTrace();
					log.error("SQL Error !:" + pstmt.toString(), e);
					throw e;
				} finally {
					pstmt.close();
				}
				
				return result;
}

	@Override
	public Agent updateAgent(Agent agent) throws Exception {
		Agent result = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			// Prepare the SQL query
			String sql = "UPDATE agent SET nom = ?, prenom = ?, grade = ?, competences = ?, date_naissance = ?, actif = ?, date_prise_service = ? WHERE id_arme=?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, agent.getNom());
			pstmt.setString(++i, agent.getPrenom());
			pstmt.setString(++i, agent.getGrade());
			pstmt.setString(++i, agent.getCompetences());
			pstmt.setDate(++i, agent.getDateNaissance());
			pstmt.setBoolean(++i, agent.isActif());
			pstmt.setDate(++i, agent.getPriseService());
			
			
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
			int resultCount = pstmt.executeUpdate();
			if(resultCount != 1)
				throw new Exception("Suspect non trouvé !");
			
			result = agent;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return result;
		
	}
	
	private Agent getAgentFromResultSet(ResultSet rs) throws SQLException {
		Agent agent = new Agent();
		agent.setNom(rs.getString("nom"));
		agent.setPrenom(rs.getString("prenom"));
		agent.setGrade(rs.getString("grade"));
		agent.setCompetences(rs.getString("competences"));
		agent.setDateNaissance(rs.getDate("date_naissance"));
		agent.setActif(rs.getBoolean("actif"));
		agent.setPriseService(rs.getDate("date_prise_service"));
		
		return agent;
}
	private void logSQL(PreparedStatement pstmt) {
		String sql;
		
		if (pstmt == null)
			return;
		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);

	}

}
