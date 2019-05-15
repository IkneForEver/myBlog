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
@WebServlet("/BlogDescription")
public class BlogDescriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final MockBlogDao mockBlogDao = new MockBlogDao();
	private Blog blog;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogDescriptionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int idBlog = (int) request.getAttribute("idBlog");
		blog = mockBlogDao.findById(idBlog);
		request.setAttribute("blog", blog);
		request.getRequestDispatcher("Blog.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
