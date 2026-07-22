<%@page import="model.OrdineBean"%>
<%@page import="model.RigaOrdineBean"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>GamesHub - Dettagli Ordine</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/dettaglioOrdine.css"/>
</head>
<body>

    <%@ include file="shared/header.jsp"%>
	
    <% OrdineBean ordine = (OrdineBean) request.getAttribute("ordine"); %>
	
    <div class="container-dettagli-ordine">
    	<h3>Dettaglio Ordine #<%= ordine.getId() %></h3>

        <table class="tabella-dettagli-ordine">
        	<thead>
            	<tr>
                	<th>Immagine</th>
                    <th>Nome Prodotto</th>
                    <th>Descrizione</th>
                    <th>Prezzo d'Acquisto</th>
                    <th>Metodo di Pagamento</th>
                </tr>
            </thead>
            <tbody>
            <% for(RigaOrdineBean riga : ordine.getRigheOrdine()) { 
               	String base64Image = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(riga.getImmagine());
			%>
            	<tr>
                	<td>
                		<img src="<%= base64Image %>" alt="<%= riga.getNome() %>" class="img-prodotto-ordine"/>
                    </td>
                            
                    <td class="cella-nome">
                    	<strong class="nome-prodotto-ordine"><%= riga.getNome() %></strong>
                    </td>
                            	
                    <td>
                    	<span><%= riga.getDescrizione()%></span>
                    </td>
                            
                    <td>
                    	<span class="prezzo-testo">€ <%= String.format("%.2f", riga.getPrezzoAcquisto()) %></span>
                    </td>
                            
                    <td>
                    	<span><%= ordine.getMetodoPagamentoBean().getTipologia() %></span>
                     </td>
                 </tr>
             <% } %>
             </tbody>
        </table>
    </div>
</body>
</html>