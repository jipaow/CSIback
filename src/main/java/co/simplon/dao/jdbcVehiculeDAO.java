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
import co.simplon.model.Vehicule;

public class jdbcVehiculeDAO implements VehiculeDAO {

	private DataSource datasource;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public jdbcVehiculeDAO(JdbcTemplate jdbcTemplate) {
		this.datasource = jdbcTemplate.getDataSource();
	}

	@Override
	public List<Vehicule> listVehicule() throws Exception {
		Vehicule vehicule;
		PreparedStatement pstmt = null;
		ResultSet rs;
		String sql;
		ArrayList <Vehicule> listVehicule = new ArrayList<Vehicule>();
		
		try {
			// Prepare la requete sql
			sql = "SELECT * FROM vehicule";
			pstmt = datasource.getConnection().prepareStatement(sql);
			
			// Run la requete
			rs = pstmt.executeQuery();
			
			// Log info
			logSQL(pstmt);

			// gere le resultat de la requete
			while (rs.next()) {
				vehicule = getVehiculeFromResultSet(rs);
				listVehicule.add(vehicule);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		
		return listVehicule;
	
	}

	@Override
	public Vehicule getVehicule(int id) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs;
		Vehicule vehicule = null;

		try {
			// Prepare requet sql
			String sql = "SELECT * FROM vehicule WHERE id_vehicule = ?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setInt(1, id);

			// Log info
			logSQL(pstmt);

			// Run requete
			rs = pstmt.executeQuery();
			
			// gere les resultats de requete
			if (rs.next())
				vehicule = getVehiculeFromResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}
		return vehicule;
	
	}

	@Override
	public Vehicule insertVehicule(Vehicule vehicule) throws Exception {
		PreparedStatement pstmt = null;
		Vehicule result = null;
		int i = 0;
		
		// TODO
		// force auto incremente en initialisant à 0, sinon erreur sql si id
		// existant
		//suspect.setId(new int(0));

		try {
			// Prepare the SQL query
			String sql = "INSERT INTO vehicule ( type, marque, modele, immatriculation, couleur) VALUES (?,?,?,?,?)";
			pstmt = datasource.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(++i, vehicule.getType());
			pstmt.setString(++i, vehicule.getMarque());
			pstmt.setString(++i, vehicule.getModele());
			pstmt.setString(++i, vehicule.getImmatriculation());
			pstmt.setString(++i, vehicule.getCouleur());
			
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
	public Vehicule updateVehicule(Vehicule vehicule) throws Exception {
		Vehicule result = null;
		PreparedStatement pstmt = null;
		int i = 0;

		try {
			// Prepare the SQL query
			String sql = "UPDATE vehicule SET type = ?, marque = ?, modele = ?, immatriculation = ?, couleur = ? WHERE id_vehicule=?";
			pstmt = datasource.getConnection().prepareStatement(sql);
			pstmt.setString(++i, vehicule.getType());
			pstmt.setString(++i, vehicule.getMarque());
			pstmt.setString(++i, vehicule.getModele());
			pstmt.setString(++i, vehicule.getImmatriculation());
			pstmt.setString(++i, vehicule.getCouleur());

			
			// Log info
			logSQL(pstmt);
			
			// Run the the update query
			int resultCount = pstmt.executeUpdate();
			if(resultCount != 1)
				throw new Exception("Suspect non trouvé !");
			
			result = vehicule;

		} catch (SQLException e) {
			e.printStackTrace();
			log.error("SQL Error !:" + pstmt.toString(), e);
			throw e;
		} finally {
			pstmt.close();
		}

		return result;
		
	}
	
	private Vehicule getVehiculeFromResultSet(ResultSet rs) throws SQLException {
		Vehicule vehicule = new Vehicule();
		vehicule.setType(rs.getString("type"));
		vehicule.setMarque(rs.getString("marque"));
		vehicule.setModele(rs.getString("modele"));
		vehicule.setImmatriculation(rs.getString("immatriculation"));
		vehicule.setCouleur(rs.getString("couleur"));
		
		return vehicule;
}
	private void logSQL(PreparedStatement pstmt) {
		String sql;
		
		if (pstmt == null)
			return;
		
		sql = pstmt.toString().substring(pstmt.toString().indexOf(":") + 2);
		log.debug(sql);

	}

}
