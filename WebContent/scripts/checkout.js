function checkCampoGenerico(formElem, span) {
    if(formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    formElem.classList.add("input-error");
	
    if(formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    }
	
    return false;
}

function checkTestoAlfabetico(formElem, span) {
    if(formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
	
    formElem.classList.add("input-error");
    if(formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    } 
	else if(formElem.validity.patternMismatch) {
        span.innerHTML = "Il formato non è valido. Usa solo lettere";
    }
	
    return false;
}

function checkCap(formElem, span) {
    if(formElem.checkValidity()) {
    	formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    formElem.classList.add("input-error");
	
    if(formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    } 
	else if(formElem.validity.patternMismatch || formElem.validity.tooShort) {
        span.innerHTML = "Inserisci un CAP valido (5 cifre numeriche)";
    }
	
    return false;
}

function checkNumeroCarta(formElem, span) {
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
	
    formElem.classList.add("input-error");
    
	if(formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    } 
	else if(formElem.validity.patternMismatch || formElem.validity.tooShort) {
        span.innerHTML = "Inserisci un numero di carta valido (16 cifre, solo numeri)";
    }
	
    return false;
}

function checkCvc(formElem, span) {
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
	
    formElem.classList.add("input-error");
    
	if(formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    } 
	else if(formElem.validity.patternMismatch || formElem.validity.tooShort) {
        span.innerHTML = "Inserisci un CVC valido (3 cifre numeriche)";
    }
	
    return false;
}

function checkNote(formElem, span) {
    if(formElem.value.length > 200) {
        formElem.classList.add("input-error");
        span.innerHTML = "Le note non possono superare i 200 caratteri";
		
        return false;
    }
	
    formElem.classList.remove("input-error");
    span.innerHTML = "";
	
    return true;
}

function checkDataScadenza(formElem, span) {
    if (!formElem.checkValidity() && formElem.validity.valueMissing) {
        formElem.classList.add("input-error");
        span.innerHTML = "Questo campo è obbligatorio";
        return false;
    }

    const oggi = new Date();
	// il -01  mi serve poiché type="month" restituisce solo anno e mese e quindi ci aggiungo il giorno per formare la data
    const dataInserita = new Date(formElem.value + "-01");
    const dataConfrontoOggi = new Date(oggi.getFullYear(), oggi.getMonth(), 1);
	
    if(dataInserita.getTime() < dataConfrontoOggi.getTime()) {
        formElem.classList.add("input-error");
        span.innerHTML = "La carta di pagamento risulta già scaduta";
		
        return false;
    }
	
    formElem.classList.remove("input-error");
    span.innerHTML = "";
	
    return true;
}

function checkCheckoutForm() {
    let valid = true;
    let form = document.getElementById("checkoutForm");

    if(!checkCampoGenerico(form.via, document.getElementById("errorVia")))
		valid = false;
    if(!checkCampoGenerico(form.civico, document.getElementById("errorCivico")))
		valid = false;
    if(!checkTestoAlfabetico(form.citta, document.getElementById("errorCitta")))
		valid = false;
    if(!checkCap(form.cap, document.getElementById("errorCap")))
		valid = false;
    if(!checkTestoAlfabetico(form.paese, document.getElementById("errorPaese")))
		valid = false;
    
    if(!checkNumeroCarta(form.numeroCarta, document.getElementById("errorNumeroCarta")))
		valid = false;
    if(!checkTestoAlfabetico(form.nomeTitolare, document.getElementById("errorNomeTitolare")))
		valid = false;
    if(!checkTestoAlfabetico(form.cognomeTitolare, document.getElementById("errorCognomeTitolare")))
		valid = false;
	if (!checkDataScadenza(form.dataScadenza, document.getElementById("errorDataScadenza")))
		valid = false;
    if(!checkCvc(form.cvc, document.getElementById("errorCvc")))
		valid = false;
    if(!checkNote(form.note, document.getElementById("errorNote"))) 
		valid = false;

    return valid;
}