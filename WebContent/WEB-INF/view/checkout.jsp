<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GamesHub - Checkout</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/checkout.css">
    <script src="${pageContext.request.contextPath}/scripts/checkout.js"></script>
</head>
<body>
	<%@ include file="shared/header.jsp"%>
	
    <div class="form-container">
        <div class="form-card">
            
            <div class="form-header">
                <h2>Completa <span>Ordine</span></h2>
            </div>
            
            <% if (request.getAttribute("errore") != null) { %>
               	<div class="server-error"><%= request.getAttribute("errore") %></div>
            <% } 
               if(request.getAttribute("messaggio") != null) { %>
				<div class="server-success"><%= request.getAttribute("messaggio") %></div>
			<%} %>
			
            <form action="${pageContext.request.contextPath}/checkout" method="POST" id="checkoutForm" class="form" onsubmit="return checkCheckoutForm()" novalidate>
                
                <div class="section-title">Indirizzo di Spedizione</div>
                
                <div class="form-row">
                    <div class="input-group">
                        <label for="via">Via / Piazza*</label>
                        <input type="text" id="via" name="via" placeholder="Es. Via Roma" maxlength="150" required
                        onchange="checkCampoGenerico(this, document.getElementById('errorVia'))">
                        
                        <span class="error-message" id="errorVia"></span>
                    </div>
                    <div class="input-group">
                        <label for="civico">Civico*</label>
                        <input type="text" id="civico" name="civico" placeholder="10/A" maxlength="10" required
                        onchange="checkCampoGenerico(this, document.getElementById('errorCivico'))">
                        
                        <span class="error-message" id="errorCivico"></span>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="citta">Città*</label>
                        <input type="text" id="citta" name="citta" placeholder="Es. Milano" pattern="^[A-Za-zÀ-ù\s']+$" maxlength="50" required
                        onchange="checkTestoAlfabetico(this, document.getElementById('errorCitta'))">
                        
                        <span class="error-message" id="errorCitta"></span>
                    </div>
                    
                    <div class="input-group">
                        <label for="cap">CAP*</label>
                        <input type="text" id="cap" name="cap" placeholder="Es. 20100" pattern="^[0-9]{5}$" maxlength="5" minlength="5" required
                        onchange="checkCap(this, document.getElementById('errorCap'))">
                        
                        <span class="error-message" id="errorCap"></span>
                    </div>
                </div>

                <div class="input-group">
                    <label for="paese">Paese*</label>
                    <input type="text" id="paese" name="paese" placeholder="Es. Italia" pattern="^[A-Za-zÀ-ù\s']+$" maxlength="50" required
                    onchange="checkTestoAlfabetico(this, document.getElementById('errorPaese'))">
                    
                    <span class="error-message" id="errorPaese"></span>
                </div>


                <div class="section-title">Metodo di Pagamento</div>
                
                <div class="input-group">
                    <label>Tipologia Carta</label>
                    <div class="radio-container">
                        <label class="radio-label">
                            <input type="radio" name="tipologiaPagamento" value="Visa" checked> Visa
                        </label>
                        
                        <label class="radio-label">
                            <input type="radio" name="tipologiaPagamento" value="Mastercard"> Mastercard
                        </label>
                        
                        <label class="radio-label">
                            <input type="radio" name="tipologiaPagamento" value="Postepay"> Postepay
                        </label>
                    </div>
                </div>

                <div class="input-group">
                    <label for="numeroCarta">Numero Carta*</label>
                    <input type="text" id="numeroCarta" name="numeroCarta" placeholder="16 cifre consecutive" pattern="^[0-9]{16}$" maxlength="16" minlength="16" required
                    onchange="checkNumeroCarta(this, document.getElementById('errorNumeroCarta'))">
                    
                    <span class="error-message" id="errorNumeroCarta"></span>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="nomeTitolare">Nome Titolare*</label>
                        <input type="text" id="nomeTitolare" name="nomeTitolare" placeholder="Mario" pattern="^[A-Za-zÀ-ù\s']+$" maxlength="50" required
                        onchange="checkTestoAlfabetico(this, document.getElementById('errorNomeTitolare'))">
                        
                        <span class="error-message" id="errorNomeTitolare"></span>
                    </div>
                    
                    <div class="input-group">
                        <label for="cognomeTitolare">Cognome Titolare*</label>
                        <input type="text" id="cognomeTitolare" name="cognomeTitolare" placeholder="Rossi" pattern="^[A-Za-zÀ-ù\s']+$" maxlength="50" required
                        onchange="checkTestoAlfabetico(this, document.getElementById('errorCognomeTitolare'))">
                        
                        <span class="error-message" id="errorCognomeTitolare"></span>
                    </div>
                </div>

                <div class="form-row">
                    <div class="input-group">
                        <label for="dataScadenza">Scadenza*</label>
                        <input type="month" id="dataScadenza" name="dataScadenza" required>
                        
                        <span class="error-message" id="errorDataScadenza"></span>
                    </div>
                    
                    <div class="input-group">
                        <label for="cvc">CVC*</label>
                        <input type="text" id="cvc" name="cvc" placeholder="3 cifre" pattern="^[0-9]{3}$" maxlength="3" minlength="3" required
                        onchange="checkCvc(this, document.getElementById('errorCvc'))">
                        
                        <span class="error-message" id="errorCvc"></span>
                    </div>
                </div>

                <div class="input-group">
                    <label for="note">Note Ordine</label>
                    <textarea id="note" name="note" placeholder="Note per la spedizione" maxlength="200"
                    onchange="checkNote(this, document.getElementById('errorNote'))"></textarea>
                    
                    <span class="error-message" id="errorNote"></span>
                </div>

                <button type="submit" class="btn-form">Conferma ed Acquista</button>
                
                <div class="form-footer">
                    <a href="${pageContext.request.contextPath}/carrello">Torna al carrello</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>