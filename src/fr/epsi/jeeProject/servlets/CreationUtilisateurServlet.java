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
@WebServlet("/CreationUtilisateur")
public class CreationUtilisateurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final MockUtilisateurDao mockUtilisateurDao = new MockUtilisateurDao();
	private static final DateUtils dateUtils = new DateUtils();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationUtilisateurServlet() {
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
			
			String emailCreation = request.getParameter("emailcreation");
			String passwordCreation = request.getParameter("passwordcreation");
		 	String nomCreation = (String) request.getParameter("nomcreation");
		 	String isAdmin = request.getParameter("isAdmin");
		 	Boolean isAdminBoolean = false;
		 	
			String email = (String) request.getParameter("email");
		 	
		 	//recuperation de l'utilisateur courant grâce à son email
			Utilisateur utilisateurCourant = mockUtilisateurDao.findByEmail(email);
			String nom = utilisateurCourant.getNom();
			
		 	
			if(emailCreation != null & !emailCreation.isEmpty() && passwordCreation != null & !passwordCreation.isEmpty() && nomCreation != null & !nomCreation.isEmpty() ) {
				
				//Création du nouvel utilisateur en base
			 	Utilisateur user = new Utilisateur();
			 	user.setEmail(emailCreation);
			 	user.setPassword(passwordCreation);
			 	user.setNom(nomCreation);
			 	user.setDateCreation(dateUtils.getDateDuJour());
			 	if(isAdmin!=null && isAdmin.equals("on")) {
			 		isAdminBoolean = true;
			 	}
			 	user.setAdmin(isAdminBoolean);
			 	
				Utilisateur utilisateurVoulu = mockUtilisateurDao.findByEmail(emailCreation);
				if(utilisateurVoulu==null) {
					MockUtilisateurDao.create(user);
					//redirection vers la liste des blogs
					request.setAttribute("email",email);
					request.setAttribute("nom",nom);
					ServletContext context = getServletContext();
					RequestDispatcher rd = context.getRequestDispatcher("/Blogs");
					rd.forward(request, response);
				}
				//L'utilisateur est déjà présent en base
				else {
					request.setAttribute("email",email);
					request.setAttribute("nom",nom);
					request.setAttribute("erreur","Un utilisateur avec l'email "+email+" existe déjà en base !");
					request.getRequestDispatcher("creationUtilisateur.jsp").forward(request, response);
				}
			// Un des champs obligatoires n'a pas été saisi
			}
			else {
				request.setAttribute("email",email);
				request.setAttribute("nom",nom);
				request.setAttribute("erreur","Tous les champs sont obligatoires ! ");
				request.getRequestDispatcher("creationUtilisateur.jsp").forward(request, response);
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
