<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TwitterConnect</title>
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
				<a class="brand" href="#">Project name</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="/TwitterOAuthServlet">Twitter</a></li>
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
			<h1>Bienvenu chez MSF !</h1>
			<c:choose>
				<c:when test="${requestScope.test!=null}">
					<p>
						You like this website ? Rate ${requestScope.test} it on the <a
							href="http://apple.com">Apple Store</a> !<br> Thank's you !<br>
						${requestScope.authUrl}<br> ${requestScope.text2}<br>
						${requestScope.text3}<br>
					<p>
						<a href="#" class="btn btn-primary btn-large">Learn more
							&raquo;</a>
					</p>
				</c:when>
				<c:otherwise>
					<form class="navbar-form pull-right" action="server" method="post">
						<input class="span2" type="text" name="username"
							placeholder="Username"> <input class="span2"
							type="password" name="password" placeholder="Password">
						<button type="submit" class="btn">Se connecter</button>
					</form>
					<br>
					<p>
						${requestScope.authUrl}<br> ${requestScope.text2}<br>
						${requestScope.text3}<br>
						${requestScope.yop}<br>
						yop<br>
					</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="Bootstrap/js/bootstrap.min.js"></script>
</body>
</html>