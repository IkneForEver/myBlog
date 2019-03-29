package fr.epsi.jeeProject.dao.mockImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.IUtilisateurDao;
import fr.epsi.jeeProject.dao.PersistenceManager;
import fr.epsi.jeeProject.listener.StartupListener;

public class MockUtilisateurDao implements IUtilisateurDao {

	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	private static List<Utilisateur> listOfUtilisateurs;
	private static final String FIND_ALL_QUERY = "SELECT * from users";
	private static final String INSERT_QUERY = "INSERT INTO users (email,nom,date_creation, password, is_admin) values (?,?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE users set nom = ?, date_creation= ?; password = ? where email = ?";
	private static final String REMOVE_QUERY = "DELETE from users where email = ?";
	private static final String FIND_BY_ID_QUERY = "SELECT * from users where email = ?";

	public void create(Utilisateur u) {

		Connection connection = PersistenceManager.getConnection();
		try {
			PreparedStatement st = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, u.getEmail());
			st.setString(2, u.getNom());
			st.setDate(3, u.getDateCreation());
			st.setString(4, u.getPassword());
			st.executeUpdate();
			st.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("Erreur lors de la création en base de l'utilisateur " + u, e);
		}
	}
	
	@Override
	public void update(Utilisateur u){
		Connection connection = PersistenceManager.getConnection();
		try {
			PreparedStatement st = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, u.getEmail());
			st.setString(2, u.getNom());
			st.setDate(3, u.getDateCreation());
			st.setString(4, u.getEmail());
			st.executeUpdate();
			st.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("Erreur lors de la modification en base de l'utilisateur " + u, e);
		}
	}
	
	@Override
	public void remove(Utilisateur u) {

		Connection connection = PersistenceManager.getConnection();
		try {
		PreparedStatement st = connection.prepareStatement(REMOVE_QUERY, Statement.RETURN_GENERATED_KEYS);
		st.setString(1, u.getEmail());
		st.executeUpdate();
		st.close();
		connection.close();
		}catch (SQLException e) {
			logger.error("Erreur lors de la suppression en base de l'utilisateur " + u, e);
		}
	}
	
	@Override
	public Utilisateur findByEmail(String email) {
		
		Connection connection = PersistenceManager.getConnection();
		try {
			PreparedStatement st = connection.prepareStatement(FIND_BY_ID_QUERY, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			Utilisateur users = new Utilisateur();
			if (rs != null) {
				if (rs.next()) {
					users.setEmail(rs.getString("email"));
					users.setNom(rs.getString("nom"));
					users.setDateCreation(rs.getDate("date_creation"));
					users.setPassword(rs.getString("password"));
					users.setAdmin(rs.getBoolean("is_admin"));
					st.close();
					return users;
				}
			}
		} catch (SQLException e) {
			logger.error("Erreur lors de la récupération en base de l'utilisateur avec l'email " + email, e);
		}
		return null;
	}

	
	public List<Utilisateur> findAll() {
		Connection con = PersistenceManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.executeQuery();
			List<Utilisateur> liste = new ArrayList<Utilisateur>();
			if (rs != null) {
				while (rs.next()) {
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setEmail(rs.getString("email"));
					utilisateur.setNom(rs.getString("nom"));
					utilisateur.setDateCreation(rs.getDate("date_creation"));
					utilisateur.setPassword(rs.getString("password"));
					utilisateur.setAdmin(rs.getBoolean("is_admin"));
					liste.add(utilisateur);
				}
			}
			st.close();
			con.close();
			listOfUtilisateurs = liste;
			return listOfUtilisateurs;
		} catch (SQLException e) {
			logger.error("Erreur lors de la récupération des utilisateurs en base");
		}
		// si aucun utilisateur récupéré on renvoie une liste en dure pour les tests
		if (listOfUtilisateurs == null) {
			listOfUtilisateurs = new ArrayList<Utilisateur>();
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setEmail("contact@aquasys.fr");
			utilisateur.setNom("ADMIN");
			utilisateur.setAdmin(true);
			utilisateur.setDateCreation(new java.sql.Date(new Date().getTime()));
			listOfUtilisateurs.add(utilisateur);
			this.create(utilisateur);
			utilisateur = new Utilisateur();
			utilisateur.setEmail("test@aquasys.fr");
			utilisateur.setNom("TEST");
			utilisateur.setAdmin(false);
			utilisateur.setDateCreation(new java.sql.Date(new Date().getTime()));
			listOfUtilisateurs.add(utilisateur);
			this.create(utilisateur);
		}
		return listOfUtilisateurs;
	}
}
