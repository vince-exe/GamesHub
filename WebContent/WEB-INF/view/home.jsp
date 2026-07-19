<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GamesHub - Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/home.css"/>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<div class="main-container">
	    <section class="main-section">
	    	<h1 class="main-title">BENVENUTO SU GAMES<span class="highlight">HUB</span></h1>
	        <p class="main-subtitle">Il tuo portale per videogiochi e console ai prezzi più bassi del web.<br>- By gamers for gamers.</p>
	        
	        <div class="main-buttons">
	        	<a href="<%=request.getContextPath()%>/videogiochi" class="btn btn-primary">Sfoglia Videogiochi</a>
	            <a href="<%=request.getContextPath()%>/console" class="btn btn-secondary">Scopri Console</a>
	        </div>
	    </section>
		
	    <section class="features-section">
	        <div class="feature-card">
	            <h3>Consegna Istantanea</h3>
	            <p>Ricevi la tua chiave digitale immediatamente subito dopo l'acquisto, 24/7.</p>
	        </div>
	        
	        <div class="feature-card">
	            <h3>Pagamenti Sicuri</h3>
	            <p>Sistemi di pagamento crittografati e garantiti. La tua sicurezza è la nostra priorità.</p>
	        </div>
	        
	        <div class="feature-card">
	            <h3>Console Gaming</h3>
	            <p>Resta aggiornato sulle ultime console uscite sul mercato.</p>
	        </div>
	    </section>
	</div>
</body>
</html>