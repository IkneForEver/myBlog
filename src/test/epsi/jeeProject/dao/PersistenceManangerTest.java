package test.epsi.jeeProject.dao;

import java.sql.Connection;

import org.junit.Test;

import fr.epsi.jeeProject.dao.PersistenceManager;

public class PersistenceManangerTest {
	
	@Test
	public final void testConnexion() {
		Connection connection = PersistenceManager.getConnection();		
	}
	

}
