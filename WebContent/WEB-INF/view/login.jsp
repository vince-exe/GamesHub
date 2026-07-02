<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GamesHub - Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css">
<script src="${pageContext.request.contextPath}/scripts/login.js"></script>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<div class="login-container">
			<div class="login-card">
				
				<div class="login-header">
					<h2>Accedi su games<span>Hub</span></h2>
				</div>
	
				<form action="login" method="POST" class="login-form" id="logForm" method="POST" onsubmit="return checkForm()" novalidate>
					
					<div class="input-group">
						<label for="email">Email</label>
						<input type="email" id="email" name="email" placeholder="latuaemail@gmail.com" required
						onchange="checkFormElement(this, document.getElementById('errorEmail'), 'email invalida')">
						
						<span id="errorEmail" class="error-message"></span>
					</div>
		
					<div class="input-group">
						<label for="password">Password</label>
						<input type="password" id="password" name="password" placeholder="********" minlength="8" required
						onchange="validateFormElem(this, document.getElementById('errorPassword'), 'password invalida')">
						
						<span id="errorPassword" class="error-message"></span>
					</div>
	
				    <% if (request.getAttribute("errore") != null) { %>
				    	<div class="server-error">
				        	<%= request.getAttribute("errore") %>
				        </div>
				    <% } %>
            
					<button type="submit" class="btn-login">Accedi</button>
					
				</form>
				
				<div class="login-footer">
					<a href="<%=request.getContextPath()%>/registrati">Non hai un account? Registrati</a>
				</div>
	
			</div>
	</div>
</body>
</html>