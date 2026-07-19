<%@page import="model.CarrelloBean"%>
<%@page import="model.ProdottoBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/carrello.css"/>
<title>GamesHub - Il tuo Carrello</title>
<script src="${pageContext.request.contextPath}/scripts/carrello.js" defer></script>
</head>
<body>
<%@ include file="shared/header.jsp"%>

<div class="carrello-container">
    <h2 class="titolo-carrello">Il tuo <span>Carrello</span></h2>

    <% 
        List<CarrelloBean> carrello = (List<CarrelloBean>) session.getAttribute("carrello");
        double totaleComplessivo = 0.0;
        
        if(carrello == null || carrello.isEmpty()) {%>
	        <div class="box-carrello-vuoto">
	            <h3>Il tuo carrello è vuoto</h3>
	            <p>Non hai ancora aggiunto nessun gioco al tuo catalogo.</p>
	            <a href="<%=request.getContextPath()%>/catalogo" class="btn btn-primary">Vai al catalogo</a>
	        </div>
    	<% } else { %>
        <div class="carrello-layout">
            
            <div class="colonna-tabella">
                <table class="tabella-carrello">
                    <thead>
                        <tr>
                            <th>Prodotto</th>
                            <th>Prezzo</th>
                            <th>Quantità</th>
                            <th>Subtotale</th>
                            <th>Azioni</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(CarrelloBean item : carrello) { 
                        	double subtotaleItem = item.getProdotto().getPrezzo().doubleValue() * item.getQuantita();
                            totaleComplessivo += subtotaleItem;
                            int idProd = item.getProdotto().getId();
                        %>
                            <tr id="riga-prodotto-<%= idProd %>">
                                <td class="cella-nome">
                                    <span class="piattaforma-tag"><%= item.getProdotto().getPiattaforma() != null ? item.getProdotto().getPiattaforma() : item.getProdotto().getTipologia() %></span>
                                    <strong><%= item.getProdotto().getNome() %></strong>
                                </td>
                                <td>€ <%= String.format("%.2f", item.getProdotto().getPrezzo()) %></td>
                                <td>
                                    <div class="selettore-quantita">
                                        <button type="button" class="btn-qty" onclick="cambiaQuantita(<%= idProd %>, -1, '<%= request.getContextPath() %>')">-</button>
                                        
                                        <span id="quantita-<%= idProd %>" class="valore-quantita"><%= item.getQuantita() %></span>
                                        
                                        <button type="button" class="btn-qty" onclick="cambiaQuantita(<%= idProd %>, 1, '<%= request.getContextPath() %>')">+</button>
                                    </div>
                                </td>
                                <td id="subtotale-<%= idProd %>" class="cella-subtotale">
                                    € <%= String.format("%.2f", subtotaleItem) %>
                                </td>
                                <td>
                                    <button class="btn-elimina" onclick="rimuoviDalCarrello(<%= idProd %>, '<%= request.getContextPath() %>')" title="Rimuovi prodotto">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                                            <polyline points="3 6 5 6 21 6"></polyline>
                                            <path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                                        </svg>
                                    </button>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>

            <div class="colonna-checkout">
                <div class="checkout-card">
                    <h3>Riepilogo</h3>
                    <hr class="divisore-checkout">
                    <div class="dettaglio-prezzo">
                        <span>Spedizione</span>
                        <span class="gratis">Gratis (Digital Key)</span>
                    </div>
                    <div class="dettaglio-prezzo totale-riga">
                        <span>Totale</span>
                        <strong id="totale-carrello">€ <%= String.format("%.2f", totaleComplessivo) %></strong>
                    </div>
                    
                    <a href="<%= request.getContextPath() %>/checkout" class="btn-form btn-checkout">
                        Procedi al Checkout
                    </a>
                    
                    <a href="<%= request.getContextPath() %>/catalogo" class="continua-shopping">
                        &larr; Continua lo Shopping
                    </a>
                </div>
            </div>

        </div>
    <% } %>
</div>
</body>
</html>