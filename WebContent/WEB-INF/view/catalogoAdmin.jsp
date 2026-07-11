<%@page import="model.ProdottoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/catalogoAdmin.css"/>
<title>GamesHub - CatalogoAmministratore</title>
</head>
<body>
	<%@ include file="shared/header.jsp"%>	
	
	<% java.util.List<ProdottoBean> catalogo = (java.util.List<ProdottoBean>) request.getAttribute("catalogo");
		
		if(catalogo == null || catalogo.size() == 0) { %>
		<div class="box-catalogo-vuoto">
			<h4>Il catalogo è vuoto</h4>
			<h5><a href="<%=request.getContextPath()%>/aggiungiProdotto">Clicca qui</a> per aggiungere un prodotto</h5>
		</div>
		<% }
		else { %>
			<h1>NO VUOTO</h1>
		<%} %>
</body>
</html>