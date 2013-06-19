<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion à Facebook</title>
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
						<li class="active"><a href="/FacebookServlet">Facebook</a></li>
						<c:choose>
							<c:when test="${sessionScope.user==null}">
								<li><a href="/SignUpServlet">Inscription</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/LogOutServlet">Déconnexion</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
					<c:choose>
						<c:when test="${sessionScope.user==null}">
							<form class="navbar-form pull-right" action="SignInServlet"
								method="post">
								<input class="span2" type="text" name="username"
									placeholder="Pseudo"> <input class="span2"
									name="password" type="password" placeholder="Mot de passe">
								<button type="submit" class="btn">Connexion</button>
							</form>
						</c:when>
						<c:otherwise>
							<ul class="nav">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"> Bienvenue
										${sessionScope.user.username}<b class="caret"></b>
								</a>
									<ul class="dropdown-menu">
										<li><a href="LogOutServlet">Se déconnecter</a></li>
									</ul></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="hero-unit">
			<a href="${requestScope.authURL}"> Connexion</a>
		</div>
	</div>
</body>
</html>