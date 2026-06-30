<%@page import="model.ProdottoBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<% java.util.List<model.ProdottoBean> listaConsole = (java.util.List<ProdottoBean>) request.getAttribute("catalogoConsole");
	
		if(listaConsole == null) { %>
			<h1>Non sono disponibili console </h1>
		<%} 
		else { %>
			<h1>Ciao</h1>
		<%} %>	
</body>
</html>