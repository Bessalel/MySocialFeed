<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mes comptes</title>
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
						<li class="active"><a href="/AccountsServlet">Mes comptes</a></li>
						<li><a href="/LogOutServlet">Déconnexion</a></li>
					</ul>

					<ul class="nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> Bienvenue
								${sessionScope.user.username}<b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li><a href="LogOutServlet">Se déconnecter</a></li>
							</ul></li>
					</ul>

				</div>

			</div>
		</div>
	</div>
	<div class="container">
		<div class="hero-unit">
			<c:forEach var="instances" items="${requestScope.instancesMap}">
				<c:choose>
					<c:when test="${instances.key == \"Twitter\"}">
						<c:forEach var="twitter" items="${instances.value }">
						<c:set var="user" value="twitter.showUser(twitter.id)"/>
							<p>
								<tbody>
									<tr>
									${user}
									<!-- User user = (User) twitter.showUser(twitter.getId()); -->
										<%-- <td><img alt="Profile pic"
											src="${user.biggerProfileImageURL}"></td> --%>
										<%-- <td><a href="${status.user.URL}">${status.user.name}</a></td>
										<td>${status.text}</td>
										<td>${status.createdAt}</td> --%>
									</tr>
								</tbody>
								${twitter.screenName}<br>
							</p>
						</c:forEach>

					</c:when>
				</c:choose>
			</c:forEach>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>