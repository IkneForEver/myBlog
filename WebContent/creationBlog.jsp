<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Création d'un blog</title>
</head>
<body>	
	<h1>Création d'un blog</h1>
	<form action = "CreationBlog" method = "POST">
		<table>
		<tr>
			<td>
				Titre du blog
			</td>
			<td>
				<input type="text" name="titre">
			</td>
		</tr>
		<tr>
			<td>
				Description du blog
			</td>
			<td>
				<input type="text" name="description"> <br>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<br> <input type="submit" value="Créer">
			</td>
			<td colspan="40">
					<a href="listBlogs.jsp"><value="Annuler"></a>
			</td>
		</tr>
	</table>
	
	</form>
	
</body>
</html>