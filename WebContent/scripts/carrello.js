function aggiungiAlCarrello(idProdotto, contextPath) {
    const url = contextPath + "/carrello?action=add&id=" + idProdotto;
    
    fetch(url, { method: 'POST' })
    .then(response => {
        if (!response.ok) {
			alert("Impossibile aggiungere un prodotto al carrello");
			return;
        }
		alert("Prodotto aggiunto al carrello con successo!");
    })
    .catch(error => {
		alert("Si è verificato un errore durante l'aggiunta: " + error);        
    });
}

function cambiaQuantita(idProdotto, cambio, contextPath) {
    const spanQuantita = document.getElementById(`quantita-${idProdotto}`);
	
	const cambioInt = parseInt(cambio, 10);
    const quantitaAttuale = parseInt(spanQuantita.innerText.trim(), 10);
	
    const nuovaQuantita = quantitaAttuale + cambioInt;

    if (nuovaQuantita <= 0) {
        if (confirm("Vuoi rimuovere il prodotto dal carrello?")) {
            rimuoviDalCarrello(idProdotto, contextPath);
        }
    } 
	else {
		const url = contextPath + "/carrello?action=update&id=" + idProdotto + "&quantita=" + nuovaQuantita;

		fetch(url, { method: 'POST' })
		.then(response => {
		    if (!response.ok) {
		        throw new Error("Quantità massima disponibile raggiunta.");
		    }
		    return response.json();
		})
		.then(carrelloAggiornato => {
			const itemModificato = carrelloAggiornato.find(item => item.id === idProdotto);
			        
			const spanQuantita = document.getElementById(`quantita-${idProdotto}`);
			spanQuantita.innerText = itemModificato.quantita;

			const cellaSubtotale = document.getElementById(`subtotale-${idProdotto}`);
			cellaSubtotale.innerText = "€ " + itemModificato.subtotale.toFixed(2);

			ricalcolaTotaleCarrello();
		})
		.catch(error => {
		    alert("Si è verificato un errore durante l'aggiornamento: " + error);
		});
    }
}

function rimuoviDalCarrello(idProdotto, contextPath) {
    const url = contextPath + "/carrello?action=delete&id=" + idProdotto;
    
    fetch(url, { method: 'POST' })
    .then(response => {
        if (!response.ok) {
            alert("Impossibile rimuovere il prodotto dal carrello.");
            return;
        }
        
        const riga = document.getElementById(`riga-prodotto-${idProdotto}`);
		riga.remove();
        
        ricalcolaTotaleCarrello();
    })
    .catch(error => {
        alert("Si è verificato un errore durante la rimozione: " + error);
    });
}

function ricalcolaTotaleCarrello() {
    const subtotali = document.querySelectorAll('.cella-subtotale');
    let nuovoTotale = 0.0;

    subtotali.forEach(cella => {
        const valore = parseFloat(cella.innerText.replace('€', ''));
        if (!isNaN(valore)) {
            nuovoTotale += valore;
        }
    });
	
    const strongTotale = document.getElementById('totale-carrello');
    if (strongTotale) {
        strongTotale.innerText = "€ " + nuovoTotale.toFixed(2);
    }
}