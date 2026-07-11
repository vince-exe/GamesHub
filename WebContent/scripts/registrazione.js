function checkCellulare(formElem, span) {
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    
    formElem.classList.add("input-error");
    
    if (formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    } 
	else if (formElem.validity.patternMismatch) {
    	span.innerHTML = "Inserisci un numero di cellulare valido (9-10 cifre, solo numeri)";
    }
	
    return false;
}

function checkTermini(formElem, span) {
    if (formElem.checked) {
        span.innerHTML = "";
        return true;
    }
    
	span.innerHTML = "È necessario accettare i termini e le condizioni per registrarsi";
    return false;
}

function checkConfirmPassword(formElem, span, passwordElement) {
	if (!formElem.checkValidity()) {
        formElem.classList.add("input-error");
        if (formElem.validity.valueMissing) {
            span.innerHTML = "Questo campo è obbligatorio";
        } else if (formElem.validity.tooShort) {
            span.innerHTML = "La lunghezza minima consentita è di 8 caratteri";
        }
        return false;
    }

    if (passwordElement.value !== formElem.value) {
        formElem.classList.add("input-error");
        span.innerHTML = "Le due password non coincidono";
        return false;
    }
    
    formElem.classList.remove("input-error");
    span.innerHTML = "";
    return true;
}

function checkDataNascita(formElem, span) {
    if (!formElem.checkValidity() && formElem.validity.valueMissing) {
        formElem.classList.add("input-error");
        span.innerHTML = "Questo campo è obbligatorio";
        return false;
    }

    const dataInserita = new Date(formElem.value);
    const today = new Date();

    if (dataInserita.getTime() > today.getTime()) {
        formElem.classList.add("input-error");
        span.innerHTML = "La data di nascita non può essere nel futuro";
        return false;
    }

    formElem.classList.remove("input-error");
    span.innerHTML = "";
    return true;
}

function checkNomeCognome(formElem, span) {
    if (formElem.checkValidity()) {
        formElem.classList.remove("input-error");
        span.innerHTML = "";
        return true;
    }
    
    formElem.classList.add("input-error");
	
    if (formElem.validity.valueMissing) {
        span.innerHTML = "Questo campo è obbligatorio";
    } 
	else if (formElem.validity.tooShort) {
		span.innerHTML = "La lunghezza minima consentita è di 1 carattere";
    }
	else if (formElem.validity.patternMismatch) {
		span.innerHTML = "Il formato non è valido. Usa solo lettere (senza spazi o accenti)";
	}
	
    return false;
}

function checkRegistrazioneForm() {
    let valid = true;    
    let form = document.getElementById("regForm");
    
    let spanNome = document.getElementById("errorNome");
    if (!checkNomeCognome(form.nome, spanNome)) 
		valid = false;
	
    let spanCognome = document.getElementById("errorCognome");
    if (!checkNomeCognome(form.cognome, spanCognome)) 
		valid = false;

    let spanEmail = document.getElementById("errorEmail");
    if (!checkEmail(form.email, spanEmail)) 
		valid = false;
    

	let spanCellulare = document.getElementById("errorCellulare");
	if (!checkCellulare(form.cellulare, spanCellulare))
		valid = false;
		
    let spanPassword = document.getElementById("errorPassword");
    if (!checkPassword(form.password, spanPassword))
		valid = false;
    
    let spanConfermaPassword = document.getElementById("errorConfermaPassword");
    if (!checkConfirmPassword(form.confermaPassword, spanConfermaPassword, form.password))
		valid = false;

	let spanDataNascita = document.getElementById("errorData");
	if (!checkDataNascita(form.dataNascita, spanDataNascita))
		valid = false;
	
	let spanTermini = document.getElementById("errorTermini");
	if (!checkTermini(form.termini, spanTermini))
		valid = false;

    return valid;
}