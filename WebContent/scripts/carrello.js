function aggiungiAlCarrello(idProdotto, contextPath) {
    const url = contextPath + "/carrello?action=add&id=" + idProdotto;
    
    fetch(url, { method: 'POST' })
    .then(response => {
        if (!response.ok) {
			alert("Impossibile aggiungere un prodotto al carrello, STATUS: !OK");
        }
    })
    .catch(error => {
		alert("Si è verificato un errore durante l'aggiunta: " + error);        
    });
}

function rimuoviDalCarrello(idProdotto, contextPath) {
    const url = contextPath + "/carrello?action=delete&id=" + idProdotto;
    
    fetch(url, { method: 'POST' })
    .then(response => {
        if (!response.ok) {
			alert("Impossibile rimuovere il prodotto dal carrello, STATUS: !OK");
        }
        // TO-DO: da gestire logica
    })
    .catch(error => {
        alert("Si è verificato un errore durante la rimozione: " + error);
    });
}

function aggiornaQuantitaCarrello(idProdotto, nuovaQuantita, contextPath) {
    const url = contextPath + "/carrello?action=update&id=" + idProdotto + "&quantita=" + nuovaQuantita;
    
    fetch(url, { method: 'POST' })
    .then(response => {
        if (!response.ok) {
            throw new Error("Impossibile aggiornare la quantità, STATUS: !OK");
        }
        // TO-DO: da gestire logica
    })
    .catch(error => {
        alert("Si è verificato un errore durante l'aggiornamento: " + error);
    });
}