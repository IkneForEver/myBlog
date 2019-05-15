package fr.epsi.jeeProject.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.epsi.jeeProject.beans.Blog;
import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.mockImpl.MockBlogDao;
import fr.epsi.jeeProject.dao.mockImpl.MockUtilisateurDao;

/**
 * Servlet implementation class BlogsServlet
 */
@WebServlet("/Blogs")
public class BlogsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final MockBlogDao mockBlogDao = new MockBlogDao();
	private List<Blog> blogs;
	private String emailUtilisateurCourant;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//récupération de la liste des blogs
		blogs = mockBlogDao.findAll();
		
		//récupération de l'email de l'utilisateur courant
		emailUtilisateurCourant=(String) request.getAttribute("emailUtilisateurCourant");

		request.setAttribute("blogs", blogs);
		request.setAttribute("emailUtilisateurCourant",emailUtilisateurCourant);
		request.getRequestDispatcher("listBlogs.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void creerBlogBienvenue() {
		Utilisateur admin = MockUtilisateurDao.findByEmail("admin");
	 	Blog blogBienvenue = new Blog();
	 	blogBienvenue.setCreateur(admin);
	 	blogBienvenue.setTitre("Bienvenue !");
	 	blogBienvenue.setDescription("Bonjour à tous ! Ecrivez ce qui vous passe par la tête, personnne ne vous jugera !");
	 	MockBlogDao.create(blogBienvenue,admin);
	}
}
