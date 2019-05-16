	package test.epsi.jeeProject.dao.mockImpl;

	import static org.junit.Assert.assertEquals;

	import org.junit.Test;

	import fr.epsi.jeeProject.beans.Blog;
	import fr.epsi.jeeProject.beans.Utilisateur;
	import fr.epsi.jeeProject.dao.mockImpl.MockBlogDao;
	import fr.epsi.jeeProject.utils.DateUtils;

	public class MockBlogDaoTest {
		
		private static final MockBlogDao mockBlogDao = new MockBlogDao();
		private static final DateUtils dateUtils = new DateUtils();

		@Test
		public final void createTest() {
			Utilisateur userCreator= new Utilisateur();
			userCreator.setAdmin(false);
			userCreator.setDateCreation(dateUtils.getDateDuJour());
			userCreator.setEmail("emailtest");
			userCreator.setNom("nomtest");
			userCreator.setPassword("passwordtest");
			
			
			Blog blogToCreate = new Blog();
			blogToCreate.setCreateur(userCreator);;
			blogToCreate.setDateCreation(dateUtils.getDateDuJour());
			blogToCreate.setDescription("testdescription");
			blogToCreate.setTitre("testtitre");
			blogToCreate.setId(10001);
			mockBlogDao.create(blogToCreate, userCreator);
			
			Blog blogToFind = new Blog();
			blogToFind = mockBlogDao.findById(blogToCreate.getId());
			assertEquals(blogToCreate.getCreateur(),blogToFind.getCreateur());
			assertEquals(blogToCreate.getDateCreation(),blogToFind.getDateCreation());
			assertEquals(blogToCreate.getDescription(),blogToFind.getDescription());
			assertEquals(blogToCreate.getTitre(),blogToFind.getTitre());
		}
	}
