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

import co.simplon.model.Arme;

public class jdbcArmeDAO implements ArmeDAO {

	private DataSource datasource;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public jdbcArmeDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Arme> listArme() throws Exception {
		Arme arme;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList <Arme> listArme = new ArrayList<Arme>();
		
		try {
			// Prepare la requete sql
			sql = "SELECT * FROM arme";
			pstmt = datasource.getConnection().prepareStatement(sql);
			
			// Run la requete
			rs = pstmt.executeQuery();
			
			// Log info
			logSQL(pstmt);

			// gere le resultat de la requete
			while (rs.next()) {
				arme = getArmeFromResultSet(rs);
				listArme.add(arme);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
		return listArme;
	
	}

	@Override
	public Arme getArme(int id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Arme arme = null;

		try {
			// Prepare requet sql
			String sql = "SELECT * FROM arme WHERE id_arme = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);

			// Log info
			logSQL(pstmt);

			// Run requete
			rs = pstmt.executeQuery();
			
			// gere les resultats de requete
			if (rs.next())
				arme = getArmeFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return arme;
	
	}

	@Override
	public Arme insertArme(Arme arme) throws Exception {
		PreparedStatement pstmt = null;
		Arme result = null;
		int i = 0;
		
		// TODO
		// force auto incremente en initialisant à 0, sinon erreur sql si id
		// existant
		//suspect.setId(new int(0));

		try {
			// Prepare the SQL query
			String sql = "INSERT INTO arme (type_arme, numero_serie) VALUES (?,?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++i, arme.getType());
			pstmt.setInt(++i, arme.getNumeroDeSerie());
			
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
	public Arme updateArme(Arme arme) throws Exception {
		Arme result = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			// Prepare the SQL query
			String sql = "UPDATE arme SET type_arme = ?, numero_serie = ? WHERE id_arme=?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, arme.getType());
			pstmt.setInt(++i, arme.getNumeroDeSerie());
			pstmt.setInt(++i, arme.getIdArme());
			
			
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
			int resultCount = pstmt.executeUpdate();
			if(resultCount != 1)
				throw new Exception("Suspect non trouvé !");
			
			result = arme;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return result;
		
	}
	
	private Arme getArmeFromResultSet(ResultSet rs) throws SQLException {
		Arme arme = new Arme();
		arme.setIdArme(rs.getInt("id_arme"));
		arme.setType(rs.getString("type_arme"));
		arme.setNumeroDeSerie(rs.getInt("numero_serie"));
		
		return arme;
}
	private void logSQL(PreparedStatement pstmt) {
		String sql;
		
		if (pstmt == null)
			return;
		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);

	}

}
