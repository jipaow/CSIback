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

import co.simplon.model.Temoin;

public class jdbcTemoinDAO implements TemoinDAO {

	private DataSource datasource;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public jdbcTemoinDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Temoin> listTemoin() throws Exception {
		Temoin temoin;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList <Temoin> listTemoin = new ArrayList<Temoin>();
		
		try {
			// Prepare la requete sql
			sql = "SELECT * FROM temoin";
			pstmt = datasource.getConnection().prepareStatement(sql);
			
			// Run la requete
			rs = pstmt.executeQuery();
			
			// Log info
			logSQL(pstmt);

			// gere le resultat de la requete
			while (rs.next()) {
				temoin = getTemoinFromResultSet(rs);
				listTemoin.add(temoin);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
		return listTemoin;
	
	}

	@Override
	public Temoin getTemoin(int id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Temoin temoin = null;

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
				temoin = getTemoinFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return temoin;
	
	}

	@Override
	public Temoin insertTemoin(Temoin temoin) throws Exception {
		PreparedStatement pstmt = null;
		Temoin result = null;
		int i = 0;
		
		// TODO
		// force auto incremente en initialisant à 0, sinon erreur sql si id
		// existant
		//suspect.setId(new int(0));

		try {
			// Prepare the SQL query
			String sql = "INSERT INTO temoin ( nom, prenom, genre, date_naissance, adresse, telephone, temoignage, enquete_id) VALUES (?,?,?,?,?,?,?,?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++i, temoin.getNom());
			pstmt.setString(++i, temoin.getPrenom());
			pstmt.setString(++i, temoin.getGenre());
			pstmt.setDate(++i, temoin.getDateNaissance());
			pstmt.setString(++i, temoin.getAdresse());
			pstmt.setInt(++i, temoin.getTelephone());
			pstmt.setString(++i, temoin.getTemoignage());
			pstmt.setInt(++i, temoin.getIdEnquete());
	
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
	public Temoin updateTemoin(Temoin temoin) throws Exception {
		Temoin result = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			// Prepare the SQL query
			String sql = "UPDATE temoin SET nom = ?, prenom = ?, genre = ?, date_naissance = ?, adresse=?, telephone=?, temoignage=?, enquete_id=? WHERE id_temoin=?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, temoin.getNom());
			pstmt.setString(++i, temoin.getPrenom());
			pstmt.setString(++i, temoin.getGenre());
			pstmt.setDate(++i, temoin.getDateNaissance());
			pstmt.setString(++i, temoin.getAdresse());
			pstmt.setInt(++i, temoin.getTelephone());
			pstmt.setString(++i, temoin.getTemoignage());
			pstmt.setInt(++i, temoin.getIdEnquete());
			pstmt.setInt(++i, temoin.getId());
			
			
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
			int resultCount = pstmt.executeUpdate();
			if(resultCount != 1)
				throw new Exception("Suspect non trouvé !");
			
			result = temoin;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return result;
		
	}
	
	private Temoin getTemoinFromResultSet(ResultSet rs) throws SQLException {
		Temoin temoin = new Temoin();
		temoin.setId(rs.getInt("id_victime"));
		temoin.setNom(rs.getString("nom"));
		temoin.setPrenom(rs.getString("prenom"));
		temoin.setGenre(rs.getString("genre"));
		temoin.setDateNaissance( rs.getDate("date_naissance"));
		temoin.setAdresse(rs.getString("adresse"));
		temoin.setTelephone(rs.getInt("telephone"));
		temoin.setTelephone(rs.getInt("temoignage"));
		temoin.setIdEnquete(rs.getInt("enquete_id"));

		return temoin;
}
	private void logSQL(PreparedStatement pstmt) {
		String sql;
		
		if (pstmt == null)
			return;
		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);

	}

}
