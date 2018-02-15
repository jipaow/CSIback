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
import org.springframework.stereotype.Repository;

import co.simplon.model.Enquete;
import co.simplon.model.Suspect;



@Repository
public class jdbcSuspectDAO implements SuspectDAO {
	
	private DataSource datasource;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public jdbcSuspectDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Suspect> listSuspect() throws Exception {
		Suspect suspect;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList <Suspect> listSuspect = new ArrayList<Suspect>();
		
		try {
			// Prepare la requete sql
			sql = "SELECT * FROM suspect";
			pstmt = datasource.getConnection().prepareStatement(sql);
			
			// Run la requete
			rs = pstmt.executeQuery();
			
			// Log info
			logSQL(pstmt);

			// gere le resultat de la requete
			while (rs.next()) {
				suspect = getSuspectFromResultSet(rs);
				listSuspect.add(suspect);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
		return listSuspect;
	}

	@Override
	public Suspect getSuspect(String nom) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Suspect suspect = null;

		try {
			// Prepare requet sql
			String sql = "SELECT * FROM suspect WHERE nom = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(1, nom);

			// Log info
			logSQL(pstmt);

			// Run requete
			rs = pstmt.executeQuery();
			
			// gere les resultats de requete
			if (rs.next())
				suspect = getSuspectFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return suspect;
	
	}

	@Override
	public Suspect insertSuspect(Suspect suspect) throws Exception {
		PreparedStatement pstmt = null;
		Suspect result = null;
		int i = 0;
		
		// TODO
		// force auto incremente en initialisant à 0, sinon erreur sql si id
		// existant
		//suspect.setId(new Long(0));

		try {
			// Prepare the SQL query
			String sql = "INSERT INTO suspect ( nom, prenom, genre, date_naissance, origine, taille, poids, adresse_connue, signe_distinctif,photo, empreinte, casier,condamantion, type_condamnation ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++i, suspect.getNom());
			pstmt.setString(++i, suspect.getPrenom());
			pstmt.setString(++i, suspect.getGenre());
			pstmt.setDate(++i, suspect.getDateNaissance());
			pstmt.setString(++i, suspect.getNationalité());
			pstmt.setFloat(++i, suspect.getTaille());
			pstmt.setInt(++i, suspect.getPoid());
			pstmt.setString(++i, suspect.getAdresseConnues());
			pstmt.setString(++i, suspect.getSigneDistinctif());
			pstmt.setString(++i, suspect.getPhoto());
			pstmt.setString(++i, suspect.getEmpreinte());
			pstmt.setBoolean(++i, suspect.isCasierJudiciaire());
			pstmt.setInt(++i, suspect.getCondamnations());
			pstmt.setString(++i, suspect.getTypeCondamnation());
			
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
	public Suspect addSuspectToEnquete(Suspect suspect , Enquete enquete) throws Exception {
		PreparedStatement pstmt = null;
		Suspect result = null;
		int i = 0;
		
		try {
			String sql = "INSERT INTO enquete_has_suspect(suspect_id, enquete_id) VALUES ((SELECT id_suspect FROM suspect WHERE nom=? AND prenom=?),?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++i, suspect.getNom());
			pstmt.setString(++i, suspect.getPrenom());
			pstmt.setInt(++i, enquete.getNumeroDossier());
			
			// Log info
		    logSQL(pstmt);
						
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
	public Suspect updateSuspect(Suspect suspect) throws Exception {
		Suspect result = null;
		PreparedStatement pstmt = null;
		int i = 0;
			
		try {
			// Prepare the SQL query
			String sql = "UPDATE actor SET nom = ?, prenom = ?, genre = ?, date_naissance = ?, origine=? ,taille= ?, poids=?, adresse_connue=?, signe_distinctif=?, photo=?, empreinte=?, casier=?, condamnation=?, type_condamnation=? WHERE nom = ? AND prenom=?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, suspect.getNom());
			pstmt.setString(++i, suspect.getPrenom());
			pstmt.setString(++i, suspect.getGenre());
			pstmt.setDate(++i, suspect.getDateNaissance());
			pstmt.setString(++i, suspect.getNationalité());
			pstmt.setFloat(++i, suspect.getTaille());
			pstmt.setInt(++i, suspect.getPoid());
			pstmt.setString(++i, suspect.getAdresseConnues());
			pstmt.setString(++i, suspect.getSigneDistinctif());
			pstmt.setString(++i, suspect.getPhoto());
			pstmt.setString(++i, suspect.getEmpreinte());
			pstmt.setBoolean(++i, suspect.isCasierJudiciaire());
			pstmt.setInt(++i, suspect.getCondamnations());
			pstmt.setString(++i, suspect.getTypeCondamnation());
			
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
	
	private Suspect getSuspectFromResultSet(ResultSet rs) throws SQLException {
		Suspect suspect = new Suspect();
		suspect.setNom(rs.getString("nom"));
		suspect.setPrenom(rs.getString("prenom"));
		suspect.setGenre(rs.getString("genre"));
		suspect.setDateNaissance( rs.getDate("date_naissance"));
		suspect.setNationalité(rs.getString("origine"));
		suspect.setTaille(rs.getFloat("taille"));
	    suspect.setPoid(rs.getInt("poids"));
		suspect.setAdresseConnues(rs.getString("adresse_connue"));
		suspect.setSigneDistinctif(rs.getString("signe_distinctif"));
    	suspect.setPhoto(rs.getString("photo"));
		suspect.setEmpreinte(rs.getString("empreinte"));
		suspect.setCasierJudiciaire(rs.getBoolean("casier"));
		suspect.setCondamnations(rs.getInt("condamnation"));
		suspect.setTypeCondamnation(rs.getString("type_condamantion"));
	
		
		return suspect;	
	}
	
	private void logSQL(PreparedStatement pstmt) {
		String sql;
		
		if (pstmt == null)
			return;
		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);

	}

}
