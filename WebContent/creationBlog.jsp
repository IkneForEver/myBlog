<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Création d'un blog</title>
</head>
<body>
	<table>
		<tr>
			<td>Utilisateur : <%
				out.println(request.getAttribute("email"));
			%>
			</td>
		</tr>
	</table>
	<h1>Création d'un blog</h1>
	<form action="CreationBlog" method="POST">
		<input type="hidden" name="email"
			value="<%out.print(request.getAttribute("email"));%>">
		<table>
			<tr>
				<td>Titre du blog</td>
				<td><input type="text" name="titre"></td>
			</tr>
			<tr>
				<td>Description du blog</td>
				<td><input type="text" name="description"> <br></td>
			</tr>

			<tr>
				<td colspan="2"><br> <input type="submit" value="Créer">
				</td>
				</form>
				<form action="Annulation">
					<input type="hidden" name="email"
						value="<%out.print(request.getAttribute("email"));%>">
				<td colspan="40"><input type="submit" value="Annuler"></a>
				</td>
				</form>
			</tr>
		</table>
</body>
</html>