<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<td>Utilisateur : <%
				out.println(request.getAttribute("nom"));
			%>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td colspan="40">
				<%
				Blog blog = (Blog)request.getAttribute("blog");
				out.println("<h1>"+blog.getTitre()+"</h1>");
				%>
			</td>
			<td colspan="40">
				<form action="Annulation">
					<input type="hidden" name="email"
						value="<%out.print(request.getAttribute("email"));%>">
				<td colspan="40"><input type="submit" value="Annuler"></a>
				</td>
				</form>
			</td>
		</tr>

		<%
			out.println("<tr>");
			out.println("<td>"+blog.getDescription()+"</td>");
			out.println("</tr>");
			%>
		
	</table>




</body>
</html>