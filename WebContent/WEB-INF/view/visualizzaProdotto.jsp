<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.ProdottoBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GamesHub - Visualizza Prodotto</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/visualizzaProdotto.css" />
</head>
<body>
	<%@ include file="shared/header.jsp"%>

	<% ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
		if(prodotto != null) {
	%>
		<div class="dettaglio-prodotto-container">
			<div class="contenitore-immagine">
				<img src="${pageContext.request.contextPath}/visualizzaImmagine?id=<%= prodotto.getId() %>" 
				 	alt="Copertina <%= prodotto.getNome() %>" 
					class="immagine-dettaglio">
			</div>
			
			<div class="form-container">
				<div class="form-card">
		
					<div class="form-header">
						<h2>
							Visualizza <span>Prodotto</span>
						</h2>
					</div>
		
		
					<div class="form-row">
						<div class="input-group">
							<label for="nome">Nome Prodotto</label>
							 <input type="text" id="nome" readonly value="<%=prodotto.getNome()%>">
						</div>
		
						<div class="input-group">
							<label for="casaProduttrice">Casa Produttrice</label>
							<input type="text" id="casaProduttrice" readonly value="<%=prodotto.getCasaProduttrice()%>">
						</div>
					</div>
		
					<div class="form-row">
						<div class="input-group">
							<label for="prezzo">Prezzo (€)</label>
							<input type="number" id="prezzo" readonly value="<%=prodotto.getPrezzo()%>">
						</div>
		
						<div class="input-group">
							<label for="disponibilita">Disponibilità</label> <input
								type="number" id="disponibilità" readonly value="<%=prodotto.getDisponibilità()%>">
						</div>
					</div>
		
					<div class="form-row">
						<div class="input-group">
							<label for="tipologia">Tipologia</label> 
							<input id="tipologia" type="text" readonly value="<%=prodotto.getTipologia()%>">
						</div>
							
						<div class="input-group">
							<label for="genere">Genere</label>
							<input type="text" id="genere" readonly value="<%=prodotto.getGenere() != null ? prodotto.getGenere() : "-"%>">
						</div>
					</div>
		
					<div class="form-row">
						<div class="input-group">
							<label for="piattaforma">Piattaforma</label>
							<input type="text" id="piattaforma" readonly value="<%=prodotto.getPiattaforma() != null ? prodotto.getPiattaforma() : "-"%>">
						</div>
					</div>
		
					<div class="input-group">
						<label for="chiaveAttivazione">Chiave di Attivazione</label>
						<input type="text" id="chiaveAttivazione" readonly value="<%=prodotto.getChiaveAttivazione() != null ? prodotto.getChiaveAttivazione() : "-"%>">
					</div>
		
					<div class="input-group">
						<label for="descrizione">Descrizione Prodotto</label>
						<textarea id="descrizione" readonly><%=prodotto.getDescrizione()%></textarea>
					</div>
					
					<h4>
						<a class="torna-al-catalogo" href="<%=request.getContextPath()%>/catalogo">Torna al catalogo</a>
					</h4>
				</div>
			</div>
		</div>
	<%} else {%>
		<div class="prodotto-nontrovato">
			<div class="prodotto-nontrovato-scelta">
				<h4>Il prodotto cercato non esiste</h4>
				<h4><a href="<%=request.getContextPath()%>/catalogoAdmin">Visualizza il catalogo</a></h4>
			</div>
		</div>
	<%}%>
</body>
</html>