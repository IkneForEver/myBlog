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
@WebServlet("/RecuperationUtilisateur")
public class RecuperationUtilisateur extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final MockUtilisateurDao utilisateurDao = new MockUtilisateurDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperationUtilisateur() {
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
		 			 
			if( email != null ) {
				//redirection vers la creation des blogs avec l'utilisateur en paramètre
				request.setAttribute("email",email);
				request.getRequestDispatcher("creationBlog.jsp").forward(request, response);
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
