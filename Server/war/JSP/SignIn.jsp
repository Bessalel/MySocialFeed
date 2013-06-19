<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form class="navbar-form pull-right" action="SignInServlet"
		method="post">
		<input class="span2" type="text" name="username" placeholder="Pseudo">
		<input class="span2" name="password" type="password"
			placeholder="Mot de passe">
		<button type="submit" class="btn">Connexion</button>
	</form>
</body>
</html>