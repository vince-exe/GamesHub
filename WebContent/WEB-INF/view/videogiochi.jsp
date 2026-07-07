<%@page import="model.ProdottoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/videogiochi.css"/>
<title>GamesHub - Videogiochi</title>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<% java.util.List<model.ProdottoBean> listaVideogiochi = (java.util.List<ProdottoBean>) request.getAttribute("catalogoVideogiochi");
		
		if(listaVideogiochi == null) { %>
			<div class="empty-state-container">
        		<div class="empty-state-card">
		            <svg class="empty-state-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
		                <path stroke-linecap="round" stroke-linejoin="round" d="M15.182 15.182a4.5 4.5 0 01-6.364 0M21 12a9 9 0 11-18 0 9 9 0 0118 0zM9.75 9.75c0 .414-.168.75-.375.75S9 10.164 9 9.75 9.168 9 9.375 9s.375.336.375.75zm-.375 0h.008v.015h-.008V9.75zm5.625 0c0 .414-.168.75-.375.75s-.375-.336-.375-.75.168-.75.375-.75.375.336.375.75zm-.375 0h.008v.015h-.008V9.75z" />
		            </svg>
	            	<h2>Nessun contenuto disponibile</h2>
	            	<p>Al momento il catalogo di GamesHub è vuoto. Torna a trovarci presto per scoprire i nuovi arrivi!</p>
        		</div>
    		</div>
		<%} 
		else { %>
			<h1>Ciao</h1>
		<%} %>	
</body>
</html>