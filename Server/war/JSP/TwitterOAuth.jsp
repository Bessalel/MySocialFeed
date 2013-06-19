<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TwitterConnect</title>
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
				<a class="brand" href="#">Project name</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="/">Home</a></li>
						<li class="active"><a href="/TwitterOAuthServlet">Twitter</a></li>
						<li><a href="#contact">Contact</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Dropdown <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li class="divider"></li>
								<li class="nav-header">Nav header</li>
								<li><a href="#">Separated link</a></li>
								<li><a href="#">One more separated link</a></li>
							</ul></li>
					</ul>
					<form class="navbar-form pull-right">
						<input class="span2" type="text" placeholder="Email"> <input
							class="span2" type="password" placeholder="Password">
						<button type="submit" class="btn">Sign in</button>
					</form>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>
	<div class="container">
		<!-- Main hero unit for a primary marketing message or call to action -->
		<div class="hero-unit">

			<h2>Poster un message</h2>
			<form method="post" action="/TwitterOAuthServlet">
				<p>
					<label for="status">Quoi de neuf ?</label><br />
					<textarea name="status" id="status"></textarea>
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

			<p>Vous souhaitez ajouter un compte Twitter à votre compte MSF ?











			
			<form action="TwitterOAuthServlet" method="post">
				Entrez un nom pour ce compte (ceci n'est pas l'identifiant du
				compte, mais un nom de votre choix) : <input class="span2"
					type="text" name="accountName" placeholder="Nom du compte">
				<button type="submit" class="btn">Ajouter le compte</button>
			</form>

			</p>

		</div>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>