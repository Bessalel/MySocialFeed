<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MySocialFeed</title>
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
				<a class="brand" href="#">MySocialFeed</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="#">Accueil</a></li>
						<li><a href="/TwitterOAuthServlet">Twitter</a></li>
						<c:choose>
							<c:when test="${sessionScope.user==null}">
								<li><a href="/SignUpServlet">Inscription</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/LogOutServlet">Déconnexion</a></li>
							</c:otherwise>
						</c:choose>
						<!-- <li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Dropdown <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li class="divider"></li>
								<li class="nav-header">Nav header</li>
								<li><a href="#">Separated link</a></li>
								<li><a href="#">One more separated link</a></li>
							</ul></li> -->
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
									data-toggle="dropdown"> Bienvenue ${sessionScope.user.username}<b
										class="caret"></b>
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
		<!-- Main hero unit for a primary marketing message or call to action -->
		<div class="hero-unit">
			<h1>Bienvenu chez MSF ${sessionScope.user.username}!</h1>

			<p>
				<%-- You like this website ? Rate ${requestScope.test} it on the <a
					href="http://apple.com">Apple Store</a> !<br> Thank's you !<br>
				${requestScope.authUrl}<br> ${requestScope.text2}<br>
				${requestScope.text3}<br> --%>
			<p>
				<a href="#" class="btn btn-primary btn-large">Learn more &raquo;</a>
			</p>
			Cliquez sur le lien suivant, puis acceptez l'invitation afin de
			connecter Twitter à MSF :<br> <a href="${requestScope.authUrl}">Connectez
				vous à Twitter !</a><br>

			<table>
				<c:forEach var="user" items="${requestScope.users}">
					<tr>
						<td><c:out value="${user.username}" /></td>
						<td><c:out value="${user.password}" /></td>
						<td><c:out value="${user.email}" /></td>
					</tr>
				</c:forEach>
			</table>


			<form class="navbar-form pull-right" action="TwitterOAuthServlet"
				method="post">
				Entrez le PIN : <input class="span2" type="text" name="pin"
					placeholder="PIN">
				<button type="submit" class="btn">Valider le PIN</button>
			</form>

		</div>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>