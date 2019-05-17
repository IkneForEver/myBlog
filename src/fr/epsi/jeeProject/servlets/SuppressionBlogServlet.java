package fr.epsi.jeeProject.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet("/SuppressionBlog")
public class SuppressionBlogServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final MockBlogDao mockBlogDao = new MockBlogDao();


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuppressionBlogServlet() {
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
			
			String idBlog = request.getParameter("idBlog");
			Blog blog = mockBlogDao.findById(Integer.parseInt(idBlog));
		 	String email = (String) request.getParameter("email");
		 	mockBlogDao.remove(blog);
		 	RequestDispatcher rd = request.getRequestDispatcher("/Blogs");
			rd.forward(request, response);
			
			request.setAttribute("email",email);
			request.getRequestDispatcher("creationBlog.jsp").forward(request, response);
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
