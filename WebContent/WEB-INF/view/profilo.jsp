<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.UtenteBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    // Recuperiamo l'oggetto utente passato come attributo della richiesta
    UtenteBean utente = (UtenteBean) request.getAttribute("utente");
    
    // Inizializziamo delle stringhe vuote di sicurezza in caso l'oggetto sia nullo
    String nome = "", cognome = "", email = "", telefono = "", sesso = "", ruolo = "", dataNascitaStr = "";
    
    if (utente != null) {
        nome = utente.getNome() != null ? utente.getNome() : "";
        cognome = utente.getCognome() != null ? utente.getCognome() : "";
        email = utente.getEmail() != null ? utente.getEmail() : "";
        telefono = utente.getTelefono() != null ? utente.getTelefono() : "";
        sesso = utente.getSesso() != null ? utente.getSesso() : "";
        ruolo = utente.getRuolo() != null ? utente.getRuolo() : "";
        
        // Formattiamo il Timestamp in formato utile per l'input date (yyyy-MM-dd)
        if (utente.getDataNascita() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dataNascitaStr = sdf.format(utente.getDataNascita());
        }
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
    <title>GamesHub - Profilo Utente</title>
</head>
<body>

    <div class="form-container">
        <div class="form-card" style="max-width: 650px;"> <div class="form-header">
                <h2>Profilo <span>Utente</span></h2>
                <p style="color: #a0aec0; font-size: 14px; margin-top: 5px;">Visualizza e modifica le tue informazioni personali</p>
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

            <form class="form" action="${pageContext.request.contextPath}/UpdateProfilo" method="POST">
                
                <input type="hidden" name="id" value="<%= utente != null ? utente.getId() : "" %>">

                <div class="form-row">
                    <div class="input-group">
                        <label for="nome">Nome</label>
                        <input type="text" id="nome" name="nome" value="<%= nome %>" required placeholder="Inserisci il tuo nome">
                        <span class="error-message"></span>
                    </div>
                    <div class="input-group">
                        <label for="cognome">Cognome</label>
                        <input type="text" id="cognome" name="cognome" value="<%= cognome %>" required placeholder="Inserisci il tuo cognome">
                        <span class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="email">Email (Non modificabile)</label>
                        <input type="email" id="email" name="email" value="<%= email %>" readonly style="opacity: 0.6; cursor: not-allowed;">
                        <span class="error-message"></span>
                    </div>
                    <div class="input-group">
                        <label for="telefono">Telefono</label>
                        <input type="tel" id="telefono" name="telefono" value="<%= telefono %>" placeholder="Es. 3331234567">
                        <span class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="dataNascita">Data di Nascita</label>
                        <input type="date" id="dataNascita" name="dataNascita" value="<%= dataNascitaStr %>">
                        <span class="error-message"></span>
                    </div>
                    
                    <div class="input-group">
                        <label>Genere</label>
                        <div class="radio-container">
                            <label class="radio-label">
                                <input type="radio" name="sesso" value="M" <%= "M".equalsIgnoreCase(sesso) ? "checked" : "" %>> M
                            </label>
                            <label class="radio-label">
                                <input type="radio" name="sesso" value="F" <%= "F".equalsIgnoreCase(sesso) ? "checked" : "" %>> F
                            </label>
                            <label class="radio-label">
                                <input type="radio" name="sesso" value="Altro" <%= "Altro".equalsIgnoreCase(sesso) ? "checked" : "" %>> Altro
                            </label>
                        </div>
                        <span class="error-message"></span>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group" style="flex: 1;">
                        <label for="password">Nuova Password</label>
                        <input type="password" id="password" name="password" placeholder="Lascia vuoto per non modificarla">
                        <span class="error-message"></span>
                    </div>
                    <div class="input-group" style="flex: 0.5;">
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