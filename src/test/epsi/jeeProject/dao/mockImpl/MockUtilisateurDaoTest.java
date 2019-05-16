package test.epsi.jeeProject.dao.mockImpl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.mockImpl.MockUtilisateurDao;
import fr.epsi.jeeProject.utils.DateUtils;

public class MockUtilisateurDaoTest {
	
	private static final MockUtilisateurDao mockUtilisateurDao = new MockUtilisateurDao();
	private static final DateUtils dateUtils = new DateUtils();

	@Test
	public final void createTest() {
		Utilisateur userToCreate = new Utilisateur();
		userToCreate.setAdmin(false);
		userToCreate.setDateCreation(dateUtils.getDateDuJour());
		userToCreate.setEmail("emailtest");
		userToCreate.setNom("nomtest");
		userToCreate.setPassword("passwordtest");
		mockUtilisateurDao.create(userToCreate);
		
		Utilisateur userToFind = new Utilisateur();
		userToFind = mockUtilisateurDao.findByEmail(userToCreate.getEmail());
		assertEquals(userToCreate.getAdmin(),userToFind.getAdmin());
		assertEquals(userToCreate.getDateCreation(),userToFind.getDateCreation());
		assertEquals(userToCreate.getEmail(),userToFind.getEmail());
		assertEquals(userToCreate.getNom(),userToFind.getNom());
		assertEquals(userToCreate.getPassword(),userToFind.getPassword());
	}
}
