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

import co.simplon.model.Victime;

public class jdbcVictimeDAO implements VictimeDAO {
	
	private DataSource datasource;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public jdbcVictimeDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Victime> listVictime() throws Exception {
		Victime victime;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList <Victime> listVictime = new ArrayList<Victime>();
		
		try {
			// Prepare la requete sql
			sql = "SELECT * FROM victime";
			pstmt = datasource.getConnection().prepareStatement(sql);
			
			// Run la requete
			rs = pstmt.executeQuery();
			
			// Log info
			logSQL(pstmt);

			// gere le resultat de la requete
			while (rs.next()) {
				victime = getVictimeFromResultSet(rs);
				listVictime.add(victime);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
		return listVictime;
	
	}

	@Override
	public Victime getVictime(int id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Victime victime = null;

		try {
			// Prepare requet sql
			String sql = "SELECT * FROM victime WHERE id_victime = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);

			// Log info
			logSQL(pstmt);

			// Run requete
			rs = pstmt.executeQuery();
			
			// gere les resultats de requete
			if (rs.next())
				victime = getVictimeFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return victime;
	
	}

	@Override
	public Victime insertVictime(Victime victime) throws Exception {
		PreparedStatement pstmt = null;
		Victime result = null;
		int i = 0;
		
		// TODO
		// force auto incremente en initialisant à 0, sinon erreur sql si id
		// existant
		//suspect.setId(new int(0));

		try {
			// Prepare the SQL query
			String sql = "INSERT INTO victime ( nom, prenom, genre, date_naissance, origine, adresse, victime_de, date_deces, signe_distinctif,photo, enquete_id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++i, victime.getNom());
			pstmt.setString(++i, victime.getPrenom());
			pstmt.setString(++i, victime.getGenre());
			pstmt.setDate(++i, victime.getDateNaissance());
			pstmt.setString(++i, victime.getOrigine());
			pstmt.setString(++i, victime.getAdresse());
			pstmt.setString(++i, victime.getVictimeDe());
			pstmt.setDate(++i, victime.getDateDeces());
			pstmt.setString(++i, victime.getSigneDistinctif());
			pstmt.setString(++i, victime.getPhoto());
			pstmt.setInt(++i, victime.getIdEnquete());
	
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
	public Victime updateVictime(Victime victime) throws Exception {
		Victime result = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			// Prepare the SQL query
			String sql = "UPDATE victime SET nom = ?, prenom = ?, genre = ?, date_naissance = ?, origine=?, adresse=?, victime_de=?, date_deces=?, signe_distinctif=?, photo=?, enquete_id=? WHERE id_victime=?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, victime.getNom());
			pstmt.setString(++i, victime.getPrenom());
			pstmt.setString(++i, victime.getGenre());
			pstmt.setDate(++i, victime.getDateNaissance());
			pstmt.setString(++i, victime.getOrigine());
			pstmt.setString(++i, victime.getAdresse());
			pstmt.setString(++i, victime.getVictimeDe());
			pstmt.setDate(++i, victime.getDateDeces());
			pstmt.setString(++i, victime.getSigneDistinctif());
			pstmt.setString(++i, victime.getPhoto());
			pstmt.setInt(++i, victime.getIdEnquete());
			pstmt.setInt(++i, victime.getId());
			
			
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
			int resultCount = pstmt.executeUpdate();
			if(resultCount != 1)
				throw new Exception("Suspect non trouvé !");
			
			result = victime;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return result;
		
	}
	
	private Victime getVictimeFromResultSet(ResultSet rs) throws SQLException {
		Victime victime = new Victime();
		victime.setId(rs.getInt("id_victime"));
		victime.setNom(rs.getString("nom"));
		victime.setPrenom(rs.getString("prenom"));
		victime.setGenre(rs.getString("genre"));
		victime.setDateNaissance( rs.getDate("date_naissance"));
		victime.setOrigine(rs.getString("origine"));
		victime.setAdresse(rs.getString("adresse"));
		victime.setVictimeDe(rs.getString("victime_de"));
		victime.setDateDeces(rs.getDate("date_deces"));
		victime.setSigneDistinctif(rs.getString("signe_distinctif"));
		victime.setPhoto(rs.getString("photo"));
		victime.setIdEnquete(rs.getInt("enquete_id"));

		return victime;
}
	private void logSQL(PreparedStatement pstmt) {
		String sql;
		
		if (pstmt == null)
			return;
		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);

	}
}