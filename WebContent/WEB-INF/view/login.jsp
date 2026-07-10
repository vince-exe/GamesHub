<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>GamesHub - Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<script src="${pageContext.request.contextPath}/scripts/auth.js"></script>
<script src="${pageContext.request.contextPath}/scripts/login.js"></script>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<div class="form-container">
		<div class="form-card">
				
			<div class="form-header">
				<h2>Accedi su games<span>Hub</span></h2>
			</div>
	
			<form action="login" method="POST" class="login-form" id="logForm" onsubmit="return checkLoginForm()" novalidate>
					
				<div class="input-group">
					<label for="email">Email</label>
					<input type="email" id="email" name="email" placeholder="latuaemail@gmail.com" maxlength="30" minlength="11" required
					onchange="checkEmail(this, document.getElementById('errorEmail'))">
						
					<span id="errorEmail" class="error-message"></span>
				</div>
		
				<div class="input-group">
					<label for="password">Password</label>
					<input type="password" id="password" name="password" placeholder="********" maxlength="20" minlength="8" autocomplete="current-password" required
					onchange="checkPassword(this, document.getElementById('errorPassword'))">
						
					<span id="errorPassword" class="error-message"></span>
				</div>
		
				<% if (request.getAttribute("errore") != null) { %>
					<div class="server-error">
				    	<%= request.getAttribute("errore") %>
				    </div>
				<% } %>
            
				<button type="submit" class="btn-form">Accedi</button>
					
			</form>
				
			<div class="form-footer">
				<h4>Non hai un account? <a href="<%=request.getContextPath()%>/registrazione">Registrati</a></h4>
			</div>
		</div>
	</div>
</body>
</html>