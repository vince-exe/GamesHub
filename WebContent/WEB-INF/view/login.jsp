<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GamesHub - Login</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css">
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
	<div class="login-container">
			<div class="login-card">
				
				<div class="login-header">
					<h2>Accedi su games<span>Hub</span></h2>
				</div>
	
				<form action="LoginServlet" method="POST" style="display: flex; flex-direction: column; gap: 20px;">
					
					<div class="input-group">
						<label for="email">Email</label>
						<input type="email" id="email" name="email" placeholder="latuaemail@gmail.com" required>
					</div>
	
					<div class="input-group">
						<label for="password">Password</label>
						<input type="password" id="password" name="password" placeholder="********" required>
					</div>
	
					<button type="submit" class="btn-login">Accedi</button>
					
				</form>
				
				<div class="login-footer">
					<a href="<%=request.getContextPath()%>/registrati">Non hai un account? Registrati</a>
				</div>
	
			</div>
	</div>
</body>
</html>