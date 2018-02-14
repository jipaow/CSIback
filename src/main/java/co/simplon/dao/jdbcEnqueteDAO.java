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

import co.simplon.model.Enquete;


public class jdbcEnqueteDAO implements EnqueteDAO {
	
	private DataSource dataSource;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public jdbcEnqueteDAO(JdbcTemplate jdbcTemplate) {
		this.dataSource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Enquete> listEnquete() throws Exception {
		Enquete enquete;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList <Enquete> listOfEnquete = new ArrayList<>();
		
		try {
			// Prepare la requete sql
			sql = "SELECT id_enquete ,nom_enquete ,type_enquete FROM enquete ";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			
			// Run la requete
			rs = pstmt.executeQuery();
			
			// Log info
			logSQL(pstmt);

			// gere le resultat de la requete
			while (rs.next()) {
				enquete = getEnqueteFromResultSet(rs);
				listOfEnquete.add(enquete);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
		return listOfEnquete;

	}

	@Override
	public Enquete getEnquete(int numeroDossier) throws Exception {
		
		PreparedStatement pstmt = null;
		ResultSet rs;
		Enquete enquete = null;

		try {
			// Prepare requet sql
			String sql = "SELECT * FROM suspect WHERE id_enquete = ?";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setInt(1, numeroDossier);

			// Log info
			logSQL(pstmt);

			// Run requete
			rs = pstmt.executeQuery();
			
			// gere les resultats de requete
			if (rs.next())
				enquete = getEnqueteFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return enquete;
	
	}

	
	@Override
	public Enquete insertEnquete(Enquete enquete) throws Exception {
		
		PreparedStatement pstmt = null;
		Enquete result = null;
		int i = 0;
		
		// TODO
		// force auto incremente en initialisant à 0, sinon erreur sql si id
		// existant
		//suspect.setId(new Long(0));

		try {
			// Prepare the SQL query
			String sql = "insert into enquete (nom_enquete,type_enquete, date_creation, localisation, statut) values (?,?,?,?,?)";
			pstmt = dataSource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++i, enquete.getNom());
			pstmt.setString(++i, enquete.getCategorie());
			pstmt.setDate(++i, enquete.getDateCreation());
			pstmt.setString(++i, enquete.getLocalisation());
			pstmt.setString(++i, enquete.getStatut());
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
			//pstmt.executeUpdate();

			// TODO 
			// recupération de l'id genere, et maj de l'acteur avec l'id et la date de modif
//			ResultSet rs = pstmt.getGeneratedKeys();
//			if (rs.next()) {
//				suspect.setId(rs.getLong(1));
//				suspect.setLastUpdate(updateTime);
//				
//				result = suspect;
//			}
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
	public Enquete updateEnquete(Enquete enquete) throws Exception {
		Enquete result = null;
		PreparedStatement pstmt = null;
		int i = 0;
			
		try {
			// Prepare the SQL query
			String sql = "UPDATE enquete SET nom_enquete =?, type_enquete=?, date_creation=?,localisation=?,statut=? WHERE id_enquete = ?";
			pstmt = dataSource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, enquete.getNom());
			pstmt.setString(++i, enquete.getCategorie());
			pstmt.setDate(++i, enquete.getDateCreation());
			pstmt.setString(++i, enquete.getLocalisation());
			pstmt.setString(++i, enquete.getStatut());
	
			
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
//			int resultCount = pstmt.executeUpdate();
//			if(resultCount != 1)
//				throw new Exception("Actor not found !");
//			
//			suspect.setLastUpdate(updateTime);
//			result = suspect;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return result;
		
	}
	
	private Enquete getEnqueteFromResultSet(ResultSet rs) throws SQLException {
		
		Enquete enquete = new Enquete();
		enquete.setNumeroDossier(rs.getInt("id_enquete"));
		
		return enquete;
		
	}

	

	private void logSQL(PreparedStatement pstmt) {
	String sql;
		
		if (pstmt == null)
			return;
		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);
		
	}
	
}
