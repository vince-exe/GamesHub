<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
	<script src="${pageContext.request.contextPath}/scripts/auth.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/registrazione.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/profilo.js"></script>
    <title>GamesHub - Profilo</title>
</head>
<body>
	<%@ include file="shared/header.jsp"%>

	<%
	    UtenteBean utenteSessione = (UtenteBean) session.getAttribute("utente");
			
		String nome = utenteSessione.getNome() != null ? utenteSessione.getNome() : "";
		String cognome = utenteSessione.getCognome() != null ? utenteSessione.getCognome() : "";
		String email = utenteSessione.getEmail() != null ? utenteSessione.getEmail() : "";
		String telefono = utenteSessione.getTelefono() != null ? utenteSessione.getTelefono() : "";
		String sesso = utenteSessione.getSesso() != null ? utenteSessione.getSesso() : "";
		String ruolo = utenteSessione.getRuolo() != null ? utenteSessione.getRuolo() : "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dataNascitaStr = sdf.format(utente.getDataNascita());
	%>
	
    <div class="form-container">
        <div class="form-card">
        	<div class="form-header">
                <h2>Profilo <span>Utente</span></h2>
            </div>

            <% if (request.getAttribute("messaggio") != null) { %>
                <div class="server-success">
                    <%= request.getAttribute("messaggio") %>
                </div>
            <% } %>
            <% if (request.getAttribute("errore") != null) { %>
                <div class="server-error">
                    <%= request.getAttribute("errore") %>
                </div>
            <% } %>

            <form class="form" action="${pageContext.request.contextPath}/profilo?id=<%=utenteSessione.getId()%>" method="POST" onsubmit="return checkProfiloForm()">
               
                <div class="form-row">
                    <div class="input-group">
                        <label for="nome">Nome</label>
                        <input type="text" id="nome" name="nome" value="<%=nome%>" required
                        onchange="checkNomeCognome(this, document.getElementById('errorNome'))">
                        
                        <span id="errorNome" class="error-message"></span>
                    </div>
                    
                    <div class="input-group">
                        <label for="cognome">Cognome</label>
                        <input type="text" id="cognome" name="cognome" value="<%=cognome%>" required
                        onchange="checkNomeCognome(this, document.getElementById('errorCognome'))">
                        
                        <span id="errorCognome" class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="dataNascita">Data di Nascita</label>
                        <input type="date" id="dataNascita" name="dataNascita" value="<%=dataNascitaStr%>" required
                        onchange="checkDataNascita(this, document.getElementById('errorData'))">
                        
                        <span id="errorData" class="error-message"></span>
                    </div>
                    
                    <div class="input-group">
                        <label for="telefono">Telefono</label>
                        <input type="tel" id="telefono" name="telefono" pattern="^[0-9]{10}$" value="<%=telefono%>" required
                        onchange="checkCellulare(this, document.getElementById('errorTelefono'))">
                        
                        <span id="errorTelefono" class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">                    
                    <div class="input-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" value="<%=email%>" required
                        onchange="checkEmail(this, document.getElementById('errorEmail'))">
                        
                        <span id="errorEmail" class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">                    
                    <div class="input-group">
                        <label for="nuovaPassword">Nuova Password</label>
                        <input type="password" id="nuovaPassword" name="nuovaPassword" autocomplete="new-password"
                        onchange="checkPassword(this, document.getElementById('errorPassword'))">
                        
                        <span id="errorPassword" class="error-message"></span>
                    </div>
                </div>
           
                <div class="form-row">                    
                    <div class="input-group">
                        <label for="confermaPassword">Conferma Password</label>
                        <input type="password" id="confermaPassword" name="confermaPassword"
                        onchange="checkConfirmPassword(this, document.getElementById('errorConfermaPassword'), document.getElementById('nuovaPassword'))">
                        
                        <span id="errorConfermaPassword" class="error-message"></span>
                    </div>
                </div>
                     
				<div class="form-row">
					<div class="input-group">
                        <label>Genere</label>
                        <div class="radio-container">
                            <label class="radio-label">
                                <input type="radio" name="sesso" value="uomo" <%= "uomo".equalsIgnoreCase(sesso) ? "checked" : "" %>>Uomo
                            </label>
                            <label class="radio-label">
                                <input type="radio" name="sesso" value="donna" <%= "donna".equalsIgnoreCase(sesso) ? "checked" : "" %>>Donna
                            </label>
                        </div>
                        <span class="error-message"></span>
                    </div>
				</div>
				
                <div class="form-row">
                    <div class="input-group">
                        <label>Ruolo</label>
                        <input type="text" value="<%= ruolo.toUpperCase() %>" readonly>
                        <span class="error-message"></span>
                    </div>
                </div>

                <button type="submit" class="btn-form">Salva Modifiche</button>
            </form>

            <div class="form-footer">
                <a href="${pageContext.request.contextPath}/<%= ruolo.equalsIgnoreCase("amministratore") ? "catalogoAdmin" : "catalogo" %>">Torna al catalogo</a>
            </div>
        </div>
    </div>
</body>
</html>