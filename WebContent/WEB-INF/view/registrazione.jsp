<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GamesHub - Registrati</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/registrazione.css">
<script src="${pageContext.request.contextPath}/scripts/auth.js"></script>
<script src="${pageContext.request.contextPath}/scripts/registrazione.js"></script>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<div class="register-container">
		<div class="register-card">
			
			<div class="register-header">
				<h2>Crea un account su Games<span>Hub</span></h2>
			</div>

			<form action="registrazione" method="POST" class="register-form" id="regForm" onsubmit="return checkRegistrazioneForm()" novalidate>
				
				<div class="form-row">
					<div class="input-group">
						<label for="nome">Nome*</label>
						<input type="text" id="nome" name="nome" placeholder="Mario" pattern="^[A-Za-z]+$" maxlength="20" minlength="1" required
						onchange="checkNomeCognome(this, document.getElementById('errorNome'))">
						
						<span id="errorNome" class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="cognome">Cognome*</label>
						<input type="text" id="cognome" name="cognome" placeholder="Rossi" pattern="^[A-Za-z]+$" maxlength="20" minlength="1" required
						onchange="checkNomeCognome(this, document.getElementById('errorCognome'))">
						
						<span id="errorCognome" class="error-message"></span>
					</div>
				</div>
				
				<div class="input-group">
					<label for="email">Email*</label>
					<input type="email" id="email" name="email" placeholder="latuaemail@gmail.com" required
					onchange="checkEmail(this, document.getElementById('errorEmail'))">
					
					<span id="errorEmail" class="error-message"></span>
				</div>
	
				<div class="input-group">
					<label for="cellulare">Cellulare*</label>
					<input type="tel" id="cellulare" name="cellulare" maxlength="10" placeholder="3331234567" pattern="^[0-9]{10}$" required
					onchange="checkCellulare(this, document.getElementById('errorCellulare'))">
					
					<span id="errorCellulare" class="error-message"></span>
				</div>

				<div class="input-group">
				    <label for="password">Password*</label>
				    <input type="password" id="password" name="password" placeholder="********" minlength="8" autocomplete="new-password" required
				    onchange="checkPassword(this, document.getElementById('errorPassword'))">
				    <span id="errorPassword" class="error-message"></span>
				</div>
				
				<div class="input-group">
				    <label for="confermaPassword">Conferma Password*</label>
				    <input type="password" id="confermaPassword" name="confermaPassword" placeholder="********" minlength="8" required
				    onchange="checkConfirmPassword(this, document.getElementById('errorConfermaPassword'), document.getElementById('password'))">
				    
				    <span id="errorConfermaPassword" class="error-message"></span>
				</div>

				<div class="form-row-genere-data">
					<div class="input-group">
						<label for="dataNascita">Data di Nascita*</label>
						<input type="date" id="dataNascita" name="dataNascita" required
						onchange="checkDataNascita(this, document.getElementById('errorData'))">
						<span id="errorData" class="error-message"></span>
					</div>

					<div class="input-group">
						<label>Genere*</label>
						<div class="radio-container">
							<label class="radio-label">
								<input type="radio" name="genere" value="uomo" checked>
								<span>Uomo</span>
							</label>
							<label class="radio-label">
								<input type="radio" name="genere" value="donna">
								<span>Donna</span>
							</label>
						</div>
						<span id="errorGenere" class="error-message"></span>
					</div>
					
					<div class="input-group">
						<div class="checkbox-container">
							<input type="checkbox" id="termini" name="termini" required
								onchange="checkTermini(this, document.getElementById('errorTermini'))">
								
							<span>Accetto i <a href="#">Termini e Condizioni</a> e l'informativa sulla <a href="#">Privacy</a></span>
						</div>
						<span id="errorTermini" class="error-message"></span>
					</div>
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
				<% }
				else { %>
					<button type="submit" class="btn-register">Registrati</button>
				<%} %>
			</form>
			
			<div class="register-footer">
				<a href="<%=request.getContextPath()%>/login">Hai già un account? Accedi</a>
			</div>

		</div>
	</div>
</body>
</html>