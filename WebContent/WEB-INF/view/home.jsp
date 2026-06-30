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
	    <section class="hero-section">
			<iframe class="hero-video" 
			        src="https://www.youtube.com/embed/inBgIhylG7s?autoplay=1&mute=1&loop=1&playlist=inBgIhylG7s&controls=0&showinfo=0&rel=0&modestbranding=1" 
			        allow="autoplay; encrypted-media"
			        allowfullscreen>
			</iframe>
	        
	        <div class="video-overlay"></div>
	        
	        <div class="hero-content">
	            <h1 class="hero-title">BENVENUTO SU <span class="highlight">GAMESHUB</span></h1>
	            <p class="hero-subtitle">Il tuo portale definitivo per chiavi digitali, videogiochi e console gaming ai prezzi più bassi del web.</p>
	            <div class="hero-buttons">
	                <a href="videogiochi" class="btn btn-primary">Sfoglia Videogiochi</a>
	                <a href="#scopri-di-piu" class="btn btn-secondary">Scopri Console</a>
	            </div>
	        </div>
	    </section>
		
	    <section id="scopri-di-piu" class="features-section">
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