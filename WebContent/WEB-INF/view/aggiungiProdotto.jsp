<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<div class="form-container">
		<div class="form-card">
			
			<div class="form-header">
				<h2>Aggiungi <span>Prodotto</span></h2>
			</div>

			<form action="inserisciProdotto" method="POST" class="register-form" enctype="multipart/form-data" novalidate>
				
				<div class="form-row">
					<div class="input-group">
						<label for="nome">Nome Prodotto*</label>
						<input type="text" id="nome" name="nome" placeholder="Es. PS5 Slim o Elden Ring" maxlength="50" required>
						<span class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="casaProduttrice">Casa Produttrice*</label>
						<input type="text" id="casaProduttrice" name="casaProduttrice" placeholder="Es. Sony, FromSoftware" maxlength="30" required>
						<span class="error-message"></span>
					</div>
				</div>

				<div class="form-row">
					<div class="input-group">
						<label for="prezzo">Prezzo (€)*</label>
						<input type="number" id="prezzo" name="prezzo" step="0.01" min="0" placeholder="49.99" required>
						<span class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="disponibilita">Disponibilità*</label>
						<input type="number" id="disponibilita" name="disponibilita" min="0" placeholder="10" required>
						<span class="error-message"></span>
					</div>
				</div>

				<div class="form-row">
					<div class="input-group">
						<label for="tipologia">Tipologia*</label>
						<select id="tipologia" name="tipologia" onchange="handleTipologiaChange(this)" required>
							<option value="" disabled selected>Seleziona tipo...</option>
							<option value="console">Console</option>
							<option value="videogioco">Videogioco</option>
						</select>
						<span class="error-message"></span>
					</div>

					<div class="input-group">
						<label for="genere">Genere*</label>
						<input type="text" id="genere" name="genere" placeholder="Es. Hardware, RPG, Azione" maxlength="20" required>
						<span class="error-message"></span>
					</div>
				</div>

				<div class="form-row">
					<div class="input-group">
						<label for="piattaforma">Piattaforma</label>
						<select id="piattaforma" name="piattaforma" disabled>
							<option value="" disabled selected>Seleziona piattaforma...</option>
							<option value="Steam">Steam</option>
							<option value="Xbox">Xbox Live</option>
							<option value="PlayStation">PSN</option>
							<option value="Nintendo">Nintendo eShop</option>
						</select>
						<span class="error-message"></span>
					</div>
				</div>

				<div class="input-group">
					<label for="chiaveAttivazione">Chiave di Attivazione</label>
					<input type="text" id="chiaveAttivazione" name="chiaveAttivazione" placeholder="AAAA-BBBB-CCCC-DDDD" maxlength="50" disabled>
					<span class="error-message"></span>
				</div>
				
				<div class="input-group">
					<label for="descrizione">Descrizione Prodotto*</label>
					<textarea id="descrizione" name="descrizione" placeholder="Inserisci una descrizione accurata..." required></textarea>
					<span class="error-message"></span>
				</div>

				<div class="input-group">
					<label for="immagine">Copertina/Immagine Prodotto*</label>
					<input type="file" id="immagine" name="immagine" accept="image/*" required>
					<span class="error-message"></span>
				</div>

				<% if (request.getAttribute("errore") != null) { %>
					<div class="server-error">
						<%= request.getAttribute("errore") %>
					</div>
				<% } %>

				<% if (request.getAttribute("messaggio") != null) { %>
					<div class="server-success">
						<%= request.getAttribute("messaggio") %>
					</div>
				<% } else { %>
					<button type="submit" class="btn-form">Aggiungi al Catalogo</button>
				<% } %>
				
				<div class="form-footer">
					<h4>Finito di aggiungere? <a href="<%=request.getContextPath()%>/aggiungiProdotto">Visualizza il catalogo</a></h4>
				</div>
			</form>
			
		</div>
	</div>
</body>
</html>