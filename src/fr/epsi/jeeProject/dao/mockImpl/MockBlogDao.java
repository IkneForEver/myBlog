package fr.epsi.jeeProject.dao.mockImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.PersistenceManager;
import fr.epsi.jeeProject.listener.StartupListener;

public class MockBlogDao {

	private static final Logger logger = LogManager.getLogger(StartupListener.class);
	private static final String FIND_ALL_QUERY = "SELECT * from blog";
	private static final String INSERT_QUERY = "INSERT INTO blog (id,titre,description, createur, date_creation) values (?,?,?,?,?)";
	private static final String UPDATE_QUERY = "UPDATE blog set titre = ?, description = ?, date_modification = ? where id = ?";
	private static final String REMOVE_QUERY = "DELETE from blog where id = ?";
	private static final String FIND_BY_ID_QUERY = "SELECT * from blog where id = ?";

	public static void create(Blog b, Utilisateur u) {
		int idBlog = 0;
		Connection connection = PersistenceManager.getConnection();
		try {
			String selectIdBlog = "SELECT NEXT VALUE asFOR SEQ_ID_BLOG FROM BLOG ";
			PreparedStatement st_select_id = connection.prepareStatement(selectIdBlog, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st_select_id.executeQuery();
			st_select_id.close();
			if(rs.next()) {
			      idBlog= rs.getInt(1);
			}
			PreparedStatement st_insert_blog = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
			st_insert_blog.setInt(1, idBlog);
			st_insert_blog.setString(2, b.getTitre());
			st_insert_blog.setString(3, b.getDescription());
			st_insert_blog.setString(4, u.getEmail());
			st_insert_blog.setDate(5, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			st_insert_blog.executeUpdate();
			st_insert_blog.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("Erreur lors de la cr�ation en base du blog " + b, e);
		}
	}

	public static void update(Blog b) {
		
		Connection connection = PersistenceManager.getConnection();
		try {
			PreparedStatement st = connection.prepareStatement(UPDATE_QUERY, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, b.getTitre());
			st.setString(2, b.getDescription());
			st.setDate(3, new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			st.setInt(4, b.getId());
			st.executeUpdate();
			st.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("Erreur lors de la modification en base du blog " + b, e);
		}
	}

	public static void remove(Blog b) {
		
		Connection connection = PersistenceManager.getConnection();
		try {
			PreparedStatement st = connection.prepareStatement(REMOVE_QUERY, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, b.getId());
			st.executeUpdate();
			st.close();
			connection.close();
		} catch (SQLException e) {
			logger.error("Erreur lors de la suppression en base du blog " + b, e);
		}
	}

	public Blog findById(int id) {

		Connection connection = PersistenceManager.getConnection();
		try {
			PreparedStatement st = connection.prepareStatement(FIND_BY_ID_QUERY, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			Blog blog = new Blog();
			if (rs != null) {
				if (rs.next()) {
					blog.setId(rs.getInt("id"));
					blog.setCreateur(MockUtilisateurDao.findByEmail(rs.getString("createur")));
					blog.setTitre(rs.getString("titre"));
					blog.setDescription(rs.getString("description"));
					blog.setDateCreation(rs.getDate("date_creation"));
					blog.setDateModification(rs.getDate("date_modification"));
					st.close();
					return blog;
				}
			}
		} catch (SQLException e) {
			logger.error("Erreur lors de la r�cup�ration en base du blog dont l'identifiant est" + id, e);
		}
		return null;
	}

	public List<Blog> findAll() {
		
		List<Blog> liste = new ArrayList<Blog>();
		Connection con = PersistenceManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(FIND_ALL_QUERY, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = st.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Blog blog = new Blog();
					blog.setId(rs.getInt("id"));
					blog.setCreateur(MockUtilisateurDao.findByEmail(rs.getString("createur")));
					blog.setTitre(rs.getString("titre"));
					blog.setDescription(rs.getString("description"));
					blog.setDateCreation(rs.getDate("date_creation"));
					blog.setDateModification(rs.getDate("date_modification"));
					liste.add(blog);
				}
			}
			st.close();
			con.close();
		} catch (SQLException e) {
			logger.error("Erreur lors de la r�cup�ration des utilisateurs en base");
		}
		return liste;
	}
}
