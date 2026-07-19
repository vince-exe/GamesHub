function aggiungiAlCarrello(idProdotto, contextPath) {
    const url = contextPath + "/carrello?action=add&id=" + idProdotto;
    
    fetch(url, { method: 'POST' })
    .then(response => {
        if (!response.ok) {
			alert("Impossibile aggiungere un prodotto al carrello, STATUS: !OK");
        }
		
        alert("Prodotto aggiunto con successo al carrello!");
    })
    .catch(error => {
		alert("Si è verificato un errore durante l'aggiunta: " + error);        
    });
}