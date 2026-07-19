<%@ page import="model.UtenteBean" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css">

<% UtenteBean utente = (UtenteBean) session.getAttribute("utente"); %>
<header class="main-header">
    <div class="header-container">
        
        <div class="header-logo">
        	<span class="logo-text">GAMES<span class="accent-text">HUB</span></span>
        </div>
		
		<div class="hamburger" id="hamburgerBtn">
            <span class="bar"></span>
            <span class="bar"></span>
            <span class="bar"></span>
        </div>
        
		<ul class="nav-menu">
			<li><a href="<%=request.getContextPath()%>/home" class="nav-link">Home</a></li>
			<li><a href="<%=request.getContextPath()%>/catalogo" class="nav-link">Catalogo</a></li>
			<%if(utente != null && "cliente".equals(utente.getRuolo())) { %>
	        	<li><a href="#" class="nav-link">Ordini</a></li>
	        <%} %>
        </ul>
		
        <div class="user-actions">
            <a href="<%= request.getContextPath() %>/<%=(utente != null) ? "profilo" : "login"%>" class="action-link login-btn">
                <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                    <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path>
                    <circle cx="12" cy="7" r="4"></circle>
                </svg>
                
                <span>
                	<%if(utente != null) { %>
                		Benvenuto, <%= utente.getNome() + " " + utente.getCognome() %>
                	<%}else {%>
                		Accedi
                	<%} %>
                </span>
            </a>
            
            <%if(utente == null || !utente.getRuolo().equals("amministratore")){ %>
	            <a href="<%=request.getContextPath()%>/carrello" class="action-link cart-btn">
	                <svg class="icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
	                    <circle cx="9" cy="21" r="1"></circle>
	                    <circle cx="20" cy="21" r="1"></circle>
	                    <path d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
	                </svg>
	                <span>Carrello</span>
	            </a>
	        <%}%>
        </div>
        
    </div>
</header>
<script src="${pageContext.request.contextPath}/scripts/header.js"></script>