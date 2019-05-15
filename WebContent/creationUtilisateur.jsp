<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Création d'un utilisateur</title>
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
	<h1>Création d'un utilisateur</h1>
			<%
				if(request.getAttribute("erreur")!= null){
					out.println(request.getAttribute("erreur"));
				}
			%>
	<form action="CreationUtilisateur" method="POST">
		<input type="hidden" name="email"
			value="<%out.print(request.getAttribute("email"));%>">
		<table>
			<tr>
				<td>Nom</td>
				<td><input type="text" name="nomcreation"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="emailcreation"> <br></td>
			</tr>
			<tr>
				<td>Mot de passe</td>
				<td><input type="text" name="passwordcreation"> <br></td>
			</tr>
			<tr>
				<td>Administrateur</td>
				<td><input type="checkbox" name="isAdmin"> <br></td>
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