package fr.epsi.jeeProject.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epsi.jeeProject.listener.StartupListener;

public class PersistenceManager {
	
	private static final String DB_URL = "jdbc:hsqldb:hsql://localhost:9003";
	private static final String DB_LOGIN = "SA";
	private static final String DB_PWD = "";
	
	private static Connection connection;
	
	private PersistenceManager() {}
	
	public  static final Logger logger = LogManager.getLogger(StartupListener.class);

	
	public static Connection getConnection(){
		try {
			if ( null == connection || connection.isClosed() ) {
	    		Class.forName("org.hsqldb.jdbcDriver");
	    		connection = DriverManager.getConnection(DB_URL,DB_LOGIN,DB_PWD);
	    		logger.info("Connexion établie sur localhost");
	    	} 
		}catch(ClassNotFoundException e) {
	    	logger.error("Driver non disponible ", e);
	    } catch(SQLException e) {
	    	logger.error("Erreur lors de la connexion sur la BDD", e);
	    }
		
		return connection;
	}
	
	public static void closeConnection() {
		try {
			if ( null != connection && !connection.isClosed() ) {
				connection.close();
			}
		} catch(SQLException e) {
	    	logger.error("Erreur lors de la fermeture de la connexion à la BDD", e);
	    }
	}
}
