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
@WebServlet("/RecuperationUtilisateurCreationUtilisateur")
public class RecuperationUtilisateurCreationUtilisateurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final MockUtilisateurDao mockUtilisateurDao = new MockUtilisateurDao();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperationUtilisateurCreationUtilisateurServlet() {
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
				//recuperation de l'utilisateur en fonction de son email
				Utilisateur user = mockUtilisateurDao.findByEmail(email);
				String nom = user.getNom();
				
				//redirection vers la creation des utilisateurs avec l'utilisateur en paramètre
				request.setAttribute("nom",nom);
				request.setAttribute("email",email);
				request.getRequestDispatcher("creationUtilisateur.jsp").forward(request, response);
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
