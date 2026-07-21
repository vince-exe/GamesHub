<%@page import="model.OrdineBean"%>
<%@page import="model.UtenteBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/catalogo.css"/>
<title>GamesHub - Elenco Ordini</title>
</head>
<body>
<%@ include file="shared/header.jsp"%>

<%  
    UtenteBean utenteSessione = (UtenteBean) session.getAttribute("utente");
    boolean amministratore = utenteSessione != null && "amministratore".equals(utenteSessione.getRuolo());
	
    java.util.List<OrdineBean> ordini = (java.util.List<OrdineBean>) request.getAttribute("ordini");
%>

<div class="container-catalogo">
    	<% if(amministratore) { %>
        	<h3>Gestione Ordini Piattaforma</h3>
        <% } else { %>
        	<h3>I Miei Ordini</h3>
        <% } %>
        
        <div class="azioni-top">
            <div class="filtro-container">
				<form action="<%=request.getContextPath()%>/ordini" method="GET" class="filtro-form">
				        
                        <label for="dataDa">Dalla data:</label>
				        <input type="date" id="dataDa" name="dataDa">
				        
                        <label for="dataA">Alla data:</label>
				        <input type="date" id="dataA" name="dataA">

                        <% if (amministratore) { %>
                            <label for="idUtenteFiltro">ID Cliente:</label>
                            <input type="number" id="idUtenteFiltro" name="idUtenteFiltro" placeholder="Es. 12">
                        <% } %>
				        
				        <button type="submit" class="btn-aggiungi btn-filtra">Filtra</button>
				    </form>
		    </div>
        </div>

		<%if(ordini == null || ordini.size() == 0) {%>
			<div class="box-catalogo-vuoto">
		        <h4>Non è stato trovato alcun ordine</h4>
		    </div>
    	<%} else {%>
        <table class="tabella-prodotti">
            <thead>
                <tr>
                    <th>ID Ordine</th>
                    <th>Data Ordine</th>
                    <% if(amministratore) { %>
                        <th>ID Cliente</th>
                    <% } %>
                    <th>Città</th>
                    <th>Totale Speso</th>
                    <th>Note</th>
                    <th>Azioni</th>
                </tr>
            </thead>
            <tbody>
                <% for(OrdineBean ordine : ordini) { %>
                    <tr>
                        <td class="cella-nome"><strong>#<%= ordine.getId() %></strong></td>
                        <td><%=ordine.getData().toString().substring(0, 16)%></td>
                        
                        <% if(amministratore) { %>
                            <td><strong><%= ordine.getIdUtente() %></strong></td>
                        <% } %>
                        
                        <td><%= ordine.getIndirizzoBean().getVia() %>, <%= ordine.getIndirizzoBean().getCittà() %></td>
                        
                        <td><strong>€ <%= String.format("%.2f", ordine.getTotale()) %></strong></td>
                        <td><%=(ordine.getNote() == null || ordine.getNote().isEmpty()) ? "-" : ordine.getNote()%></td>
                        
                        <td class="container-azioni">
                        	<a class="azione azione-visualizza"
                        	   href="<%=request.getContextPath()%>/visualizzaOrdine?id=<%=ordine.getId()%>">
	                        	<svg xmlns="http://www.w3.org/2000/svg" fill="#000000" width="20px" height="20px" viewBox="0 0 32 32" version="1.1">
		    						<path d="M16.108 10.044c-3.313 0-6 2.687-6 6s2.687 6 6 6 6-2.686 6-6-2.686-6-6-6zM16.108 20.044c-2.206 0-4.046-1.838-4.046-4.044s1.794-4 4-4c2.206 0 4 1.794 4 4s-1.748 4.044-3.954 4.044zM31.99 15.768c-0.012-0.050-0.006-0.104-0.021-0.153-0.006-0.021-0.020-0.033-0.027-0.051-0.011-0.028-0.008-0.062-0.023-0.089-2.909-6.66-9.177-10.492-15.857-10.492s-13.074 3.826-15.984 10.486c-0.012 0.028-0.010 0.057-0.021 0.089-0.007 0.020-0.021 0.030-0.028 0.049-0.015 0.050-0.009 0.103-0.019 0.154-0.018 0.090-0.035 0.178-0.035 0.269s0.017 0.177 0.035 0.268c0.010 0.050 0.003 0.105 0.019 0.152 0.006 0.023 0.021 0.032 0.028 0.052 0.010 0.027 0.008 0.061 0.021 0.089 2.91 6.658 9.242 10.428 15.922 10.428s13.011-3.762 15.92-10.422c0.015-0.029 0.012-0.058 0.023-0.090 0.007-0.017 0.020-0.030 0.026-0.050 0.015-0.049 0.011-0.102 0.021-0.154 0.018-0.090 0.034-0.177 0.034-0.27 0-0.088-0.017-0.175-0.035-0.266zM16 25.019c-5.665 0-11.242-2.986-13.982-8.99 2.714-5.983 8.365-9.047 14.044-9.047 5.678 0 11.203 3.067 13.918 9.053-2.713 5.982-8.301 8.984-13.981 8.984z"/>
								</svg>
                        	</a>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <%}%>
    </div>
</body>
</html>