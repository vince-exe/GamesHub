<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GamesHub - AggiungiProdotto</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/aggiungiProdotto.css"/>
<script src="${pageContext.request.contextPath}/scripts/aggiungiProdotto.js"></script>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<% if(request.getAttribute("messaggio") != null) { %>
	<div class="prodotto-aggiunto">
		<h4>Complimenti! Il prodotto è stato aggiunto al catalogo</h4>
		<div class="prodotto-aggiunto-scelta">
		<h4><a href="<%=request.getContextPath()%>/aggiungiProdotto">Continua</a></h4> oppure <h4><a href="<%=request.getContextPath()%>/catalogoAdmin">Vai al catalogo</a></h4>
		</div>
	</div>
	<%}
	else { %>
	<div class="form-container">
		<div class="form-card">
			
			<div class="form-header">
				<h2>Aggiungi <span>Prodotto</span></h2>
			</div>

			<form action="aggiungiProdotto" method="POST" id="aggiungiForm" class="form" enctype="multipart/form-data" novalidate onsubmit="return checkAggiungiProdottoForm()">
				
				<div class="form-row">
					<div class="input-group">
						<label for="nome">Nome Prodotto*</label>
						<input type="text" id="nome" name="nome" placeholder="PS5 Slim o Elden Ring" minlength="1" maxlength="100" required
						onchange="checkInput(this, document.getElementById('errorNome'), 1)">
						
						<span id="errorNome" class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="casaProduttrice">Casa Produttrice*</label>
						<input type="text" id="casaProduttrice" name="casaProduttrice" placeholder="Sony, FromSoftware" minlength="1" maxlength="100" required
						onchange="checkInput(this, document.getElementById('errorCasa'), 1)">
						
						<span id="errorCasa" class="error-message"></span>
					</div>
				</div>

				<div class="form-row">
					<div class="input-group">
						<label for="prezzo">Prezzo (€)</label>
						<input type="number" id="prezzo" name="prezzo" step="1.0" min="0" placeholder="49.99" value="0.00">
						
						<span id="errorPrezzo" class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="disponibilita">Disponibilità</label>
						<input type="number" id="disponibilita" name="disponibilita" min="1" placeholder="10" value="1">
						
						<span id="errorDisponibilita" class="error-message"></span>
					</div>
				</div>

				<div class="form-row">
					<div class="input-group">
						<label for="tipologia">Tipologia*</label>
						<select id="tipologia" name="tipologia" onchange="checkTipologia(this, document.getElementById('errorTipologia'))">
							<option value="" disabled selected>Seleziona tipo...</option>
							<option value="console">Console</option>
							<option value="videogioco">Videogioco</option>
						</select>
						
						<span id="errorTipologia" class="error-message"></span>
					</div>

					<div class="input-group">
						<label for="genere">Genere*</label>
						<input type="text" id="genere" name="genere" placeholder="RPG, Azione" minlength="1" maxlength="50" disabled
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
							<option value="steam">Steam</option>
							<option value="xbox">Xbox Live</option>
							<option value="playstation">PSN</option>
							<option value="nintendo">Nintendo eShop</option>
							<option value="battlenet">Battlenet</option>
							<option value="riot">RiotGames</option>
						</select>
						
						<span id="errorPiattaforma" class="error-message"></span>
					</div>
				</div>

				<div class="input-group">
					<label for="chiaveAttivazione">Chiave di Attivazione</label>
					<input type="text" id="chiaveAttivazione" name="chiaveAttivazione" placeholder="AAAA-BBBB-CCCC-DDDD" maxlength="19" pattern="[A-Za-z]{4}-[A-Za-z]{4}-[A-Za-z]{4}-[A-Za-z]{4}" disabled
					onchange="checkChiave(this, document.getElementById('errorChiave'))">
					
					<span id="errorChiave" class="error-message"></span>
				</div>
				
				<div class="input-group">
					<label for="descrizione">Descrizione Prodotto*</label>
					<textarea id="descrizione" name="descrizione" placeholder="Inserisci una descrizione accurata" minlength="10" maxlength="200" required
					onchange="checkInput(this, document.getElementById('errorDescrizione'), 10)"></textarea>
					
					<span id="errorDescrizione" class="error-message"></span>
				</div>
				
				<div class="input-group">
					<label for="immagine">Copertina/Immagine Prodotto*</label>
					<input type="file" id="immagine" name="immagine" accept="image/*" required
					onchange="checkInput(this, document.getElementById('errorImmagine'), 0)">
					
					<span id="errorImmagine" class="error-message"></span>
				</div>
				
				<% if (request.getAttribute("errore") != null) { %>
					<div class="server-error">
						<%= request.getAttribute("errore") %>
					</div>
				<% } %>
				
				<button type="submit" class="btn-form">Aggiungi al Catalogo</button>
				
				<div class="form-footer">
					<h4>Finito di aggiungere? <a href="<%=request.getContextPath()%>/catalogoAdmin">Visualizza il catalogo</a></h4>
				</div>
			</form>
			
		</div>
	</div>
	<%} %>
</body>
</html>