package fr.epsi.jeeProject.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/CreationBlog")
public class CreationBlogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final MockUtilisateurDao utilisateurDao = new MockUtilisateurDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationBlogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			String titre = request.getParameter("titre");
			String description = request.getParameter("description");
			
			//Récupération de l'utilisateur courant grâce à son email
		 	String email = (String) request.getAttribute("emailUtilisateurCourant");
		 	Utilisateur utilisateurCourant = MockUtilisateurDao.findByEmail(email);
		 	
			if(titre != null & !titre.isEmpty() && description != null & !description.isEmpty() && utilisateurCourant != null ) {
				
				//Création du nouveau blog en base
			 	Blog blogBienvenue = new Blog();
			 	blogBienvenue.setCreateur(utilisateurCourant);
			 	blogBienvenue.setTitre(titre);
			 	blogBienvenue.setDescription(description);
			 	MockBlogDao.create(blogBienvenue,utilisateurCourant);
				
				//redirection vers la liste des blogs
				ServletContext context = getServletContext();
				RequestDispatcher rd = context.getRequestDispatcher("/Blogs");
				rd.forward(request, response);
			}
			else {
				request.getRequestDispatcher("creationBlog.jsp").forward(request, response);
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
