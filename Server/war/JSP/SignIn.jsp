<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion</title>
<!-- Bootstrap -->
<link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="/">MySocialFeed</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="/">Accueil</a></li>
						<li><a href="/TwitterOAuthServlet">Twitter</a></li>
						<li><a href="/FacebookServlet">Facebook</a></li>
						<li><a href="/SignUpServlet">Inscription</a></li>

					</ul>

				</div>

			</div>
		</div>
	</div>
	<div class="container">
		<div class="hero-unit">
			<form action="SignInServlet"
				method="post">
				Veuillez entrer vos identifiants : <br />
				<input class="span2" type="text" name="username"
					placeholder="Pseudo"><br /> <input class="span2"
					name="password" type="password" placeholder="Mot de passe"><br />
				<button type="submit" class="btn">Connexion</button>
				<br />
			</form>
		</div>
	</div>
</body>
</html>