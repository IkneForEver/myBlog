<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="fr.epsi.jeeProject.beans.Blog"%>
<%@ page import="java.util.Iterator"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fil d'actualité</title>
</head>
<body>
	<table>
		<tr>
			<td>Ravi de vous retrouver <% out.print(request.getAttribute("nom")); %> ! </td>
		</tr>
		<tr>
			<td colspan="40">
				<h1>Fil d'actualité</h1>
			</td>
			<td colspan="40">
				<form action="Connexion" method="POST">
					<input type="submit" value="Quitter">
				</form>
			</td>
			<td colspan="40">
				<form action="RecuperationUtilisateurCreationBlog" method="POST">
					<input type="hidden" name="email" value= "<% out.print(request.getAttribute("email")); %>">
					<input type="submit" value="Créer un nouveau blog">
				</form>
			</td>
			<td colspan="40">
				<form action="RecuperationUtilisateurCreationUtilisateur" method="POST">
					<input type="hidden" name="email" value= "<% out.print(request.getAttribute("email")); %>">
					<input type="submit" value="Créer un nouvel utilisateur">
				</form>
			</td>
		</tr>
		</table>
		
		<%
			out.println("<\n> <\n>");
		%>
		<table>
		<tr>
			<td>SUJET</td>
			<td>AUTEUR</td>
			<td>DERNIER MSG</td>
		</tr>
		<%
			List<Blog> blogList = (ArrayList<Blog>) request.getAttribute("blogs");
			for(int i=0;i<blogList.size();i++) {
				Blog blog = blogList.get(i);
				out.println("<tr>");
				out.println("<td>");
				out.println("<a href = \"DescriptionBlog?idBlog="+blog.getId()+"&email="+request.getAttribute("email")+"\">"+blog.getTitre()+"</a>\"");
				out.println("</td>");
				out.println("<td>");
				out.println(blog.getCreateur().getNom());
				out.println("</td>");
				out.println("<td>");
				out.println(blog.getDateModification());
				out.println("</td>");
				out.println("<td>");
				//out.println("<a href = \"SuppressionBlog?idBlog="+blog.getId()+"&email="+request.getAttribute("email")+"\">"+"annuler"+"</a>\"");
				out.println("</td>");
				out.println("</tr>");
			}
		%>
		
	</table>
</body>
</html>