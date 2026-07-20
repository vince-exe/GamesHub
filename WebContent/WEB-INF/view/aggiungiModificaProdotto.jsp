<%@page import="model.ProdottoBean"%>
<%@ page import="java.math.BigDecimal" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GamesHub - AggiungiProdotto</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/aggiungiModificaProdotto.css"/>
<script src="${pageContext.request.contextPath}/scripts/aggiungiModificaProdotto.js"></script>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<%
		ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto");
		
		String nomeVal = (prodotto != null) ? prodotto.getNome() : "";
		String casaVal = (prodotto != null) ? prodotto.getCasaProduttrice() : "";
		BigDecimal prezzoVal = (prodotto != null) ? prodotto.getPrezzo() : BigDecimal.ZERO;
		int dispVal = (prodotto != null) ? prodotto.getDisponibilità() : 1;
		String tipoVal = (prodotto != null) ? prodotto.getTipologia() : "";
		String genereVal = (prodotto != null) ? prodotto.getGenere() : "";
		String piattaformaVal = (prodotto != null) ? prodotto.getPiattaforma() : "";
		String chiaveVal = (prodotto != null) ? prodotto.getChiaveAttivazione() : "";
		String descrizioneVal = (prodotto != null) ? prodotto.getDescrizione() : "";
	%>
	
	<% if(request.getAttribute("messaggio") != null) { %>
	<div class="prodotto-aggiunto">
		<h4><%=request.getAttribute("messaggio")%></h4>
		<div class="prodotto-aggiunto-scelta">
			<h4>
				<%if(prodotto != null) { %>
					<a href="<%=request.getContextPath()%>/aggiungiModificaProdotto?id=<%=prodotto.getId()%>">Modifica di Nuovo</a>
				<%} else {%>
					<a href="<%=request.getContextPath()%>/aggiungiModificaProdotto">Continua</a>
				<%} %>
			</h4> oppure <h4><a href="<%=request.getContextPath()%>/catalogo">Vai al catalogo</a></h4>
		</div>
	</div>
	<%}
	else { %>
	<div class="form-container">
		<div class="form-card">
			
			<div class="form-header">
				<%if(prodotto != null) { %>
					<h2>Modifica <span>Prodotto</span></h2>
				<%} else {%>
					<h2>Aggiungi <span>Prodotto</span></h2>
				<%} %>
			</div>
			
			<form action="aggiungiModificaProdotto<%= (prodotto != null) ? "?id=" + prodotto.getId() : "" %>" method="POST" id="aggiungiForm" class="form" enctype="multipart/form-data" novalidate onsubmit="return checkAggiungiProdottoForm()">
				
				<div class="form-row">
					<div class="input-group">
						<label for="nome">Nome Prodotto*</label>
						<input type="text" id="nome" name="nome" value="<%=nomeVal%>" placeholder="PS5 Slim o Elden Ring" minlength="1" maxlength="100" required
						onchange="checkInput(this, document.getElementById('errorNome'), 1)">
						
						<span id="errorNome" class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="casaProduttrice">Casa Produttrice*</label>
						<input type="text" id="casaProduttrice" name="casaProduttrice" value="<%=casaVal%>" placeholder="Sony, FromSoftware" minlength="1" maxlength="100" required
						onchange="checkInput(this, document.getElementById('errorCasa'), 1)">
						
						<span id="errorCasa" class="error-message"></span>
					</div>
				</div>

				<div class="form-row">
					<div class="input-group">
						<label for="prezzo">Prezzo (€)</label>
						<input type="number" id="prezzo" name="prezzo" step="1.0" min="0" value="<%=prezzoVal%>" placeholder="49.99">
						
						<span id="errorPrezzo" class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="disponibilita">Disponibilità</label>
						<input type="number" id="disponibilita" name="disponibilita" min="1" value="<%=dispVal%>" placeholder="10">
						
						<span id="errorDisponibilita" class="error-message"></span>
					</div>
				</div>

				<div class="form-row">
					<div class="input-group">
						<label for="tipologia">Tipologia*</label>
						<select id="tipologia" name="tipologia" onchange="checkTipologia(this, document.getElementById('errorTipologia'))">
							<option value="" disabled selected>Seleziona tipo...</option>
							<option value="console" <%="console".equals(tipoVal) ? "selected" : ""%>>Console</option>
							<option value="videogioco" <%="videogioco".equals(tipoVal) ? "selected" : ""%>>Videogioco</option>
						</select>
						
						<span id="errorTipologia" class="error-message"></span>
					</div>

					<div class="input-group">
						<label for="genere">Genere*</label>
						<input type="text" id="genere" name="genere" value="<%=genereVal%>" placeholder="RPG, Azione" minlength="1" maxlength="50" disabled
						onchange="checkInput(this, document.getElementById('errorGenere'), 1)">
						
						<span id="errorGenere" class="error-message"></span>
					</div>
				</div>
				
				<div class="form-row">
					<div class="input-group">
						<label for="piattaforma">Piattaforma</label>
						<select id="piattaforma" name="piattaforma" disabled 
						onchange="this.classList.remove('input-error'); document.getElementById('errorPiattaforma').innerHTML=''">
							<option value="" disabled selected>Seleziona piattaforma...</option>
							<option value="steam" <%="steam".equals(piattaformaVal) ? "selected" : ""%>>Steam</option>
							<option value="xbox" <%="xbox".equals(piattaformaVal) ? "selected" : ""%>>Xbox Live</option>
							<option value="playstation" <%="playstation".equals(piattaformaVal) ? "selected" : ""%>>PSN</option>
							<option value="nintendo" <%="nintendo".equals(piattaformaVal) ? "selected" : ""%>>Nintendo eShop</option>
							<option value="battlenet" <%="battlenet".equals(piattaformaVal) ? "selected" : ""%>>Battlenet</option>
							<option value="riot" <%="riot".equals(piattaformaVal) ? "selected" : ""%>>RiotGames</option>
						</select>
						
						<span id="errorPiattaforma" class="error-message"></span>
					</div>
				</div>

				<div class="input-group">
					<label for="chiaveAttivazione">Chiave di Attivazione</label>
					<input type="text" id="chiaveAttivazione" name="chiaveAttivazione" value="<%=chiaveVal%>" placeholder="AAAA-BBBB-CCCC-DDDD" maxlength="19" pattern="[A-Za-z]{4}-[A-Za-z]{4}-[A-Za-z]{4}-[A-Za-z]{4}" disabled
					onchange="checkChiave(this, document.getElementById('errorChiave'))">
					
					<span id="errorChiave" class="error-message"></span>
				</div>
				
				<div class="input-group">
					<label for="descrizione">Descrizione Prodotto*</label>
					<textarea id="descrizione" name="descrizione" placeholder="Inserisci una descrizione accurata" minlength="10" maxlength="200" required
					onchange="checkInput(this, document.getElementById('errorDescrizione'), 10)"><%=descrizioneVal%></textarea>
					
					<span id="errorDescrizione" class="error-message"></span>
				</div>
				
				<div class="input-group">
					<label for="immagine">
						<%if(prodotto != null) { %>
							Copertina/Immagine Prodotto (opzionale)
						<%}else { %>
							Copertina/Immagine Prodotto*
						<%} %>
					</label>
					
					<!-- Quando sto nell'opzione di modifica l'immagine è opzionale -->
					<input type="file" id="immagine" name="immagine" accept="image/*" <%= (prodotto == null) ? "required" : "" %>
					onchange="checkInput(this, document.getElementById('errorImmagine'), 0)">
					
					<span id="errorImmagine" class="error-message"></span>
				</div>
				
				<% if (request.getAttribute("errore") != null) { %>
					<div class="server-error">
						<%= request.getAttribute("errore") %>
					</div>
				<% } %>
				
				<button type="submit" class="btn-form">
					<%if(prodotto != null) { %>
						Modifica Prodotto
					<%} else { %>
						Aggiungi Prodotto
					<%} %>
				</button>
				
				<div class="form-footer">
					<%if(prodotto != null) { %>
						<h4>Non vuoi più modificare? <a href="<%=request.getContextPath()%>/catalogo">Visualizza il catalogo</a></h4>
					<%} else { %>
						<h4>Finito di aggiungere? <a href="<%=request.getContextPath()%>/catalogo">Visualizza il catalogo</a></h4>
					<%} %>
				</div>
			</form>
			
		</div>
	</div>
	<%} %>
</body>
</html>