<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    UtenteBean utente = (UtenteBean) session.getAttribute("utente");
		
	String nome = utente.getNome() != null ? utente.getNome() : "";
	String cognome = utente.getCognome() != null ? utente.getCognome() : "";
	String email = utente.getEmail() != null ? utente.getEmail() : "";
	String telefono = utente.getTelefono() != null ? utente.getTelefono() : "";
	String sesso = utente.getSesso() != null ? utente.getSesso() : "";
	String ruolo = utente.getRuolo() != null ? utente.getRuolo() : "";
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String dataNascitaStr = sdf.format(utente.getDataNascita());
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
    <title>GamesHub - Profilo</title>
</head>
<body>

    <div class="form-container">
        <div class="form-card">
        	<div class="form-header">
                <h2>Profilo <span>Utente</span></h2>
            </div>

            <% if (request.getAttribute("successo") != null) { %>
                <div class="server-success">
                    <%= request.getAttribute("successo") %>
                </div>
            <% } %>
            <% if (request.getAttribute("errore") != null) { %>
                <div class="server-error">
                    <%= request.getAttribute("errore") %>
                </div>
            <% } %>

            <form class="form" action="${pageContext.request.contextPath}/aggiornaProfilo?id=<%=utente.getId()%>" method="POST">
               
                <div class="form-row">
                    <div class="input-group">
                        <label for="nome">Nome</label>
                        <input type="text" id="nome" name="nome" value="<%=nome%>" required>
                        <span class="error-message"></span>
                    </div>
                    <div class="input-group">
                        <label for="cognome">Cognome</label>
                        <input type="text" id="cognome" name="cognome" value="<%=cognome%>" required>
                        <span class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="dataNascita">Data di Nascita</label>
                        <input type="date" id="dataNascita" name="dataNascita" value="<%=dataNascitaStr%>">
                        <span class="error-message"></span>
                    </div>
                    
                    <div class="input-group">
                        <label for="telefono">Telefono</label>
                        <input type="tel" id="telefono" name="telefono" value="<%=telefono%>">
                        <span class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">                    
                    <div class="input-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" value="<%=email%>">
                        <span class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">                    
                    <div class="input-group">
                        <label for="nuovaPassword">Nuova Password</label>
                        <input type="password" id="nuovaPassword" name="nuovaPassword">
                        <span class="error-message"></span>
                    </div>
                </div>
           
                <div class="form-row">                    
                    <div class="input-group">
                        <label for="confermaPassword">Conferma Password</label>
                        <input type="password" id="confermaPassword" name="confermaPassword">
                        <span class="error-message"></span>
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
                        <input type="text" value="<%= ruolo.toUpperCase() %>" readonly style="opacity: 0.6; cursor: not-allowed; text-align: center;">
                        <span class="error-message"></span>
                    </div>
                </div>

                <button type="submit" class="btn-form">Salva Modifiche</button>
            </form>

            <div class="form-footer">
                <a href="${pageContext.request.contextPath}/catalogo">← Torna alla Gestione Catalogo</a>
            </div>

        </div>
    </div>

</body>
</html>