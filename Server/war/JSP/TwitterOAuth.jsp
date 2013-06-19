<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion à Twitter</title>
<!-- Bootstrap -->
<link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="CSS/Design.css" rel="stylesheet" media="screen">
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
						<li class="active"><a href="/TwitterOAuthServlet">Twitter</a></li>
						<li><a href="/FacebookServlet">Facebook</a></li>
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
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container">
		<div class="hero-unit">

			<h2>Poster un message</h2>
			<form method="post" action="/TwitterOAuthServlet">
				<p>
					<label for="status">Quoi de neuf ?</label><br />
					<textarea name="status" id="status"></textarea>
					<br />
					<button type="submit" class="btn">Poster</button>
					<%-- <c:choose>
						<c:when test="${fn:length(sessionScope.accounts)} >1 ">
							<c:forEach var="account" items="${sessionScope.accounts}">
								
							</c:forEach>
						</c:when>
					</c:choose> --%>

				</p>
			</form>
			<c:choose>
				<c:when test="${requestScope.messagePosted!=null}">
					<div class="alert">
						<h3>${requestScope.messagePosted}</h3>
					</div>
				</c:when>
			</c:choose>

			<h2>Timeline</h2>
			<table class="table table-hover">
				<c:forEach var="status" items="${requestScope.statuses}">
					<tbody>
						<tr>
							<td><img alt="Profile pic"
								src="${status.user.biggerProfileImageURL}"></td>
							<td><a href="${status.user.URL}">${status.user.name}</a></td>
							<td>${status.text}</td>
							<td>${status.createdAt}</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>




			<h2>Connexion à Twitter !</h2>

			<c:choose>
				<c:when test="${requestScope.createConnection!=null}">
					<div class="alert">
						<h3>${requestScope.createConnection}</h3>
					</div>
				</c:when>
			</c:choose>

			<p>
				Vous souhaitez ajouter un compte Twitter à votre compte MSF ?<br />
			<form action="TwitterOAuthServlet" method="post">
				Entrez un nom pour ce compte (ceci n'est pas l'identifiant du
				compte, mais un nom de votre choix) : <input class="span2"
					type="text" name="accountName" placeholder="Nom du compte"><br />
				<button type="submit" class="btn">Ajouter le compte</button>
				<br />
			</form>

			</p>

		</div>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>