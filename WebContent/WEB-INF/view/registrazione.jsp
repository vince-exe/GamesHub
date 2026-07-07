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
						<label for="nome">Nome</label>
						<input type="text" id="nome" name="nome" placeholder="Mario" maxlength="20" minlength="1" required
						onchange="checkNomeCognome(this, document.getElementById('errorNome'))">
						
						<span id="errorNome" class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="cognome">Cognome</label>
						<input type="text" id="cognome" name="cognome" placeholder="Rossi" maxlength="20" minlength="1" required
						onchange="checkNomeCognome(this, document.getElementById('errorCognome'))">
						
						<span id="errorCognome" class="error-message"></span>
					</div>
				</div>
				
				<div class="input-group">
					<label for="email">Email</label>
					<input type="email" id="email" name="email" placeholder="latuaemail@gmail.com" required
					onchange="checkEmail(this, document.getElementById('errorEmail'))">
					
					<span id="errorEmail" class="error-message"></span>
				</div>
	
				<div class="input-group">
				    <label for="password">Password</label>
				    <input type="password" id="password" name="password" placeholder="********" minlength="8" autocomplete="new-password" required
				    onchange="checkPassword(this, document.getElementById('errorPassword'))">
				    <span id="errorPassword" class="error-message"></span>
				</div>
				
				<div class="input-group">
				    <label for="confermaPassword">Conferma Password</label>
				    <input type="password" id="confermaPassword" name="confermaPassword" placeholder="********" minlength="8" required
				    onchange="checkConfirmPassword(this, document.getElementById('errorConfermaPassword'), document.getElementById('password'))">
				    
				    <span id="errorConfermaPassword" class="error-message"></span>
				</div>

				<% if (request.getAttribute("errore") != null) { %>
					<div class="server-error">
						<%= request.getAttribute("errore") %>
					</div>
				<% } %>
        
				<button type="submit" class="btn-register">Registrati</button>
				
			</form>
			
			<div class="register-footer">
				<a href="<%=request.getContextPath()%>/login">Hai già un account? Accedi</a>
			</div>

		</div>
	</div>
</body>
</html>