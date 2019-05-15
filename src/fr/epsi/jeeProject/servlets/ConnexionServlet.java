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

import fr.epsi.jeeProject.beans.Utilisateur;
import fr.epsi.jeeProject.dao.mockImpl.MockUtilisateurDao;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/Connexion")
public class ConnexionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final MockUtilisateurDao utilisateurDao = new MockUtilisateurDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConnexionServlet() {
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
		boolean userFound = false;
		ArrayList<Utilisateur> users = (ArrayList<Utilisateur>) utilisateurDao.findAll();
		String email = request.getParameter("email");
		for (Utilisateur u : users) {
			if (request.getParameter("email") != null && request.getParameter("email").equals(u.getEmail())
					&& request.getParameter("password") != null
					&& request.getParameter("password").equals(u.getPassword())) {
				
				//récupération de l'utilisateur qui s'est connecté
				request.setAttribute("email",email);

				// redirection vers la servlet qui gère les blogs
				RequestDispatcher rd = request.getRequestDispatcher("/Blogs");
				userFound = true;
				rd.forward(request, response);
				
			}
		}
		if(!userFound) {
			request.getRequestDispatcher("index.html").forward(request, response);
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
