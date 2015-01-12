<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Gestion de commandes</title>
		<link rel="stylesheet" href="css/defaut.css" type="text/css"/>
	</head>
	<body>

		<header>
			<div class="headerWrapper">
				<h1>Baby Physio</h1>
			</div>
			
		</header>
		<nav>
			<div class="navWrapper">
				<ul>
					<li><a href="article.jsp">Gestion des articles</a></li>
					<li><a href="client.jsp">Gestion des clients</a></li>
					<li><a href="commande.jsp">Gestion des commandes</a></li>
				</ul>
			</div>
			
		</nav>
		
		<div class="bodyWrapper">
		<!-- AFFICHER MESSAGE SUCCES -->
			<c:if test="${!empty success}">
				<div class="success">
					<c:out value="${success}"/>
				</div>
			</c:if>
		
		<!-- AFFICHER MESSAGE ERREUR -->	 
			 <c:if test="${!empty erreurs}">
				<div class="errors">
					<c:out value="${erreurs}"/>
				</div>
			</c:if>