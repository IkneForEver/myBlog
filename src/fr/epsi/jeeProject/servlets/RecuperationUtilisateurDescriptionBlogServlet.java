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
import fr.epsi.jeeProject.utils.DateUtils;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/DescriptionBlog")
public class RecuperationUtilisateurDescriptionBlogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final MockUtilisateurDao mockUtilisateurDao = new MockUtilisateurDao();
	private static final MockBlogDao mockBlogDao = new MockBlogDao();
	private static final DateUtils dateUtils = new DateUtils();




	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperationUtilisateurDescriptionBlogServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			//Récupération de l'utilisateur courant grâce à son email
		 	String email = (String) request.getParameter("email");
		 	
		 	//Récupération de l'id du blog à afficher
		 	String idBlog = (String) request.getParameter("idBlog");
		 			 
			if( email != null ) {
				//recuperation de l'utilisateur en fonction de son email
				Utilisateur user = mockUtilisateurDao.findByEmail(email);
				String nom = user.getNom();
				
				//recuperation du blog en fonction de son id
				Blog blog = mockBlogDao.findById(Integer.parseInt(idBlog));
				
				//redirection vers la description des blogs avec l'utilisateur courant en paramètre
				request.setAttribute("nom",nom);
				request.setAttribute("email",email);
				request.setAttribute("blog", blog);
				request.getRequestDispatcher("Blog.jsp").forward(request, response);
			}
			else {
				// redirection vers la servlet qui gère les blogs
				request.setAttribute("email",email);
				RequestDispatcher rd = request.getRequestDispatcher("/Blogs");
				rd.forward(request, response);
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
