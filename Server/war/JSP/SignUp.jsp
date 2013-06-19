<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form class="navbar-form pull-right" action="SignUpServlet"
		method="post">
		<h3>Créez un compte MSF !</h3>
		Choisissez un nom d'utilisateur : <input pattern=".{5,20}" class="span2" type="text" name="username" placeholder="KarateBoy37"> 
		Entrez votre adresse mail : <input pattern=".{5,50}" class="span2" type="email" name="email" placeholder="moi@me.com"> 
		Choisissez un mot de passe : <input pattern=".{6,25}" class="span2" type="password" name="password" placeholder="*******">
		<button type="submit" class="btn">Envoyer les données !</button>
	</form>
</body>
</html>